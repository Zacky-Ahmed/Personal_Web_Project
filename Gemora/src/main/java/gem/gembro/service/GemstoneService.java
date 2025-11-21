package gem.gembro.service;

import gem.gembro.model.Gemstone;
import gem.gembro.repository.GemstoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GemstoneService {
    @Autowired
    private GemstoneRepository gemstoneRepository;

    public Gemstone createGemstone(Gemstone gemstone) {
        return gemstoneRepository.save(gemstone);
    }

    public Optional<Gemstone> getGemstoneById(Long id) {
        Optional<Gemstone> gemstone = gemstoneRepository.findById(id);
        if (gemstone.isPresent()) {
            Gemstone g = gemstone.get();
            g.setViewsCount(g.getViewsCount() + 1);
            gemstoneRepository.save(g);
        }
        return gemstone;
    }

    public List<Gemstone> getAllGemstones() {
        return gemstoneRepository.findAll();
    }

    public List<Gemstone> getActiveGemstones() {
        return gemstoneRepository.findAllActiveOrderedByPremium();
    }

    public List<Gemstone> getGemstonesBySellerId(Long sellerId) {
        return gemstoneRepository.findBySellerId(sellerId);
    }

    public List<Gemstone> getPremiumBoostedGemstones() {
        return gemstoneRepository.findByIsPremiumBoostedTrue();
    }

    public List<Gemstone> getGemstonesByType(String gemType) {
        return gemstoneRepository.findByGemType(gemType);
    }

    public List<Gemstone> getGemstonesByOrigin(String origin) {
        return gemstoneRepository.findByOrigin(origin);
    }

    public Gemstone updateGemstone(Gemstone gemstone) {
        return gemstoneRepository.save(gemstone);
    }

    public Gemstone activatePremiumBoost(Long id, int days) {
        Gemstone gemstone = gemstoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gemstone not found"));

        int effectiveDays = Math.max(2, days);
        gemstone.setIsPremiumBoosted(true);
        gemstone.setPremiumBoostExpiresAt(LocalDateTime.now().plusDays(effectiveDays));
        return gemstoneRepository.save(gemstone);
    }

    public Gemstone deactivatePremiumBoost(Long id) {
        Gemstone gemstone = gemstoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gemstone not found"));
        gemstone.setIsPremiumBoosted(false);
        gemstone.setPremiumBoostExpiresAt(null);
        return gemstoneRepository.save(gemstone);
    }

    public void deleteGemstone(Long id) {
        gemstoneRepository.deleteById(id);
    }
}

