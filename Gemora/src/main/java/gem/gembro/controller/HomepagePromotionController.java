package gem.gembro.controller;

import gem.gembro.dto.HomepagePromotionRequest;
import gem.gembro.dto.HomepagePromotionResponse;
import gem.gembro.model.HomepagePromotion;
import gem.gembro.model.HomepagePromotion.Slot;
import gem.gembro.service.HomepagePromotionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homepage/promotions")
@CrossOrigin(origins = "*")
public class HomepagePromotionController {

    private final HomepagePromotionService promotionService;

    public HomepagePromotionController(HomepagePromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<HomepagePromotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @GetMapping("/active")
    public ResponseEntity<List<HomepagePromotionResponse>> getActivePromotions() {
        return ResponseEntity.ok(promotionService.getActivePromotions());
    }

    @PutMapping("/{slot}")
    public ResponseEntity<HomepagePromotion> upsertPromotion(@PathVariable("slot") Slot slot,
                                                             @Valid @RequestBody HomepagePromotionRequest request) {
        HomepagePromotion updated = promotionService.upsertPromotion(slot, request);
        return ResponseEntity.ok(updated);
    }
}

