package gem.gembro.controller;

import gem.gembro.model.Seller;
import gem.gembro.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "*")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        try {
            Seller createdSeller = sellerService.createSeller(seller);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSeller);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id)
                .map(seller -> ResponseEntity.ok(seller))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Seller> getSellerByUserId(@PathVariable Long userId) {
        return sellerService.getSellerByUserId(userId)
                .map(seller -> ResponseEntity.ok(seller))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Seller>> getSellersByStatus(@PathVariable String status) {
        try {
            Seller.VerificationStatus verificationStatus = Seller.VerificationStatus.valueOf(status.toUpperCase());
            return ResponseEntity.ok(sellerService.getSellersByVerificationStatus(verificationStatus));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        seller.setId(id);
        return ResponseEntity.ok(sellerService.updateSeller(seller));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Seller> approveSeller(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sellerService.approveSeller(id));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Seller> rejectSeller(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sellerService.rejectSeller(id));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}

