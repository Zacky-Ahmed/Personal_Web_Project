package gem.gembro.controller;

import gem.gembro.model.Gemstone;
import gem.gembro.service.GemstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            int days = request.getOrDefault("days", 7);
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
}

