package gem.gembro.controller;

import gem.gembro.model.SellerApplication;
import gem.gembro.model.SellerApplication.ApplicationStatus;
import gem.gembro.service.SellerApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seller-applications")
@CrossOrigin(origins = "*")
public class SellerApplicationController {

    private final SellerApplicationService applicationService;
    @Value("${app.upload.nic-dir:uploads/nic}")
    private String nicUploadDir;

    public SellerApplicationController(SellerApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SellerApplication> submitApplication(
            @ModelAttribute SellerApplication application,
            @RequestPart("nicFrontFile") MultipartFile nicFrontFile,
            @RequestPart("nicBackFile") MultipartFile nicBackFile,
            @RequestPart(value = "gemRegFile", required = false) MultipartFile gemRegFile) throws IOException {

        if (nicFrontFile == null || nicFrontFile.isEmpty() || nicBackFile == null || nicBackFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        application.setNicFrontFileName(storeFile(nicFrontFile, "nic-front"));
        application.setNicBackFileName(storeFile(nicBackFile, "nic-back"));
        if (gemRegFile != null && !gemRegFile.isEmpty()) {
            application.setGemRegFileName(storeFile(gemRegFile, "gemreg"));
        }

        if (application.getUserId() != null && application.getUserId() <= 0) {
            application.setUserId(null);
        }

        SellerApplication saved = applicationService.submitApplication(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SellerApplication>> getByStatus(@PathVariable String status) {
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status.toUpperCase());
        return ResponseEntity.ok(applicationService.getApplicationsByStatus(applicationStatus));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<SellerApplication> approve(@PathVariable Long id, @RequestBody(required = false) Map<String, Long> payload) {
        Long adminId = payload != null ? payload.get("adminId") : null;
        return ResponseEntity.ok(applicationService.updateStatus(id, ApplicationStatus.APPROVED, adminId));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<SellerApplication> reject(@PathVariable Long id, @RequestBody(required = false) Map<String, Long> payload) {
        Long adminId = payload != null ? payload.get("adminId") : null;
        return ResponseEntity.ok(applicationService.updateStatus(id, ApplicationStatus.REJECTED, adminId));
    }

    private String storeFile(MultipartFile file, String prefix) throws IOException {
        Path uploadPath = Paths.get(nicUploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        String originalName = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
        String extension = "";
        int lastDot = originalName.lastIndexOf('.');
        if (lastDot != -1) {
            extension = originalName.substring(lastDot);
        }

        String filename = prefix + "-" + System.currentTimeMillis() + extension;
        Path target = uploadPath.resolve(filename);
        file.transferTo(target.toFile());

        Path relative = Paths.get("uploads", "nic", filename);
        return relative.toString().replace("\\", "/");
    }
}
