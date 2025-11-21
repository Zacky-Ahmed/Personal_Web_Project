package gem.gembro.controller;

import gem.gembro.model.Gemstone;
import gem.gembro.service.GemstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gemstones")
@CrossOrigin(origins = "*")
public class GemstoneController {
    @Autowired
    private GemstoneService gemstoneService;

    @PostMapping
    public ResponseEntity<Gemstone> createGemstone(@RequestBody Gemstone gemstone) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gemstoneService.createGemstone(gemstone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gemstone> getGemstoneById(@PathVariable Long id) {
        return gemstoneService.getGemstoneById(id)
                .map(gemstone -> ResponseEntity.ok(gemstone))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Gemstone>> getAllGemstones(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String origin) {
        
        if (status != null && status.equalsIgnoreCase("active")) {
            return ResponseEntity.ok(gemstoneService.getActiveGemstones());
        }
        if (type != null) {
            return ResponseEntity.ok(gemstoneService.getGemstonesByType(type));
        }
        if (origin != null) {
            return ResponseEntity.ok(gemstoneService.getGemstonesByOrigin(origin));
        }
        return ResponseEntity.ok(gemstoneService.getAllGemstones());
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Gemstone>> getGemstonesBySellerId(@PathVariable Long sellerId) {
        return ResponseEntity.ok(gemstoneService.getGemstonesBySellerId(sellerId));
    }

    @GetMapping("/premium")
    public ResponseEntity<List<Gemstone>> getPremiumBoostedGemstones() {
        return ResponseEntity.ok(gemstoneService.getPremiumBoostedGemstones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gemstone> updateGemstone(@PathVariable Long id, @RequestBody Gemstone gemstone) {
        gemstone.setId(id);
        return ResponseEntity.ok(gemstoneService.updateGemstone(gemstone));
    }

    @PostMapping("/{id}/premium-boost")
    public ResponseEntity<?> activatePremiumBoost(@PathVariable Long id, @RequestBody Map<String, Integer> request) {
        try {
            int days = request.getOrDefault("days", 2);
            return ResponseEntity.ok(gemstoneService.activatePremiumBoost(id, days));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/{id}/premium-boost")
    public ResponseEntity<?> deactivatePremiumBoost(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gemstoneService.deactivatePremiumBoost(id));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGemstone(@PathVariable Long id) {
        gemstoneService.deleteGemstone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/certificate/download")
    public ResponseEntity<Resource> downloadCertificate(@PathVariable Long id) {
        try {
            Gemstone gemstone = gemstoneService.getGemstoneById(id)
                    .orElseThrow(() -> new RuntimeException("Gemstone not found"));
            
            if (gemstone.getCertificateFilePath() == null || gemstone.getCertificateFilePath().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Certificate file not available for this gemstone");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            
            Path filePath = Paths.get(gemstone.getCertificateFilePath());
            File file = filePath.toFile();
            
            if (!file.exists() || !file.isFile()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Certificate file not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            
            Resource resource = new FileSystemResource(file);
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            
            String fileName = file.getName();
            if (gemstone.getCertificateNumber() != null && !gemstone.getCertificateNumber().trim().isEmpty()) {
                String extension = "";
                int lastDot = fileName.lastIndexOf('.');
                if (lastDot > 0) {
                    extension = fileName.substring(lastDot);
                }
                fileName = gemstone.getCertificateNumber().replaceAll("[^a-zA-Z0-9]", "_") + extension;
            }
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

