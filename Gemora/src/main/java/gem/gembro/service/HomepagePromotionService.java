package gem.gembro.service;

import gem.gembro.dto.HomepagePromotionRequest;
import gem.gembro.dto.HomepagePromotionResponse;
import gem.gembro.model.Gemstone;
import gem.gembro.model.HomepagePromotion;
import gem.gembro.model.HomepagePromotion.Slot;
import gem.gembro.repository.GemstoneRepository;
import gem.gembro.repository.HomepagePromotionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.*;

@Service
public class HomepagePromotionService {

    private final HomepagePromotionRepository promotionRepository;
    private final GemstoneRepository gemstoneRepository;
    private final NumberFormat lkrFormatter;

    public HomepagePromotionService(HomepagePromotionRepository promotionRepository,
                                    GemstoneRepository gemstoneRepository) {
        this.promotionRepository = promotionRepository;
        this.gemstoneRepository = gemstoneRepository;
        this.lkrFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "LK"));
        this.lkrFormatter.setMaximumFractionDigits(0);
    }

    public List<HomepagePromotion> getAllPromotions() {
        return promotionRepository.findAll(Sort.by("slot"));
    }

    public List<HomepagePromotionResponse> getActivePromotions() {
        List<HomepagePromotion> promotions = promotionRepository.findAllByActiveTrue(Sort.by("slot"));
        return buildResponses(promotions);
    }

    public HomepagePromotion upsertPromotion(Slot slot, HomepagePromotionRequest request) {
        HomepagePromotion promotion = promotionRepository.findBySlot(slot)
                .orElseGet(() -> {
                    HomepagePromotion p = new HomepagePromotion();
                    p.setSlot(slot);
                    return p;
                });

        promotion.setGemstoneId(request.getGemstoneId());
        promotion.setTitle(trimmed(request.getTitle()));
        promotion.setSubtitle(trimmed(request.getSubtitle()));
        promotion.setBadgeLabel(trimmed(request.getBadgeLabel()));
        promotion.setBadgeStyle(trimmed(request.getBadgeStyle()));
        promotion.setPriceText(trimmed(request.getPriceText()));
        promotion.setFootnoteLeft(trimmed(request.getFootnoteLeft()));
        promotion.setFootnoteRight(trimmed(request.getFootnoteRight()));
        promotion.setActive(request.getActive() != null ? request.getActive() : Boolean.TRUE);

        return promotionRepository.save(promotion);
    }

    private List<HomepagePromotionResponse> buildResponses(List<HomepagePromotion> promotions) {
        Map<Long, Gemstone> gemstoneMap = loadGemstones(promotions);
        List<HomepagePromotionResponse> responses = new ArrayList<>();

        for (HomepagePromotion promotion : promotions) {
            Gemstone gemstone = gemstoneMap.get(promotion.getGemstoneId());
            HomepagePromotionResponse.PromotionContent content = buildContent(promotion, gemstone);
            HomepagePromotionResponse.GemstoneSummary gemstoneSummary = gemstone != null
                    ? buildGemstoneSummary(gemstone)
                    : null;

            responses.add(new HomepagePromotionResponse(
                    promotion.getId(),
                    promotion.getSlot(),
                    Boolean.TRUE.equals(promotion.getActive()),
                    content,
                    gemstoneSummary
            ));
        }
        return responses;
    }

    private Map<Long, Gemstone> loadGemstones(List<HomepagePromotion> promotions) {
        Set<Long> gemstoneIds = new HashSet<>();
        promotions.stream()
                .map(HomepagePromotion::getGemstoneId)
                .filter(Objects::nonNull)
                .forEach(gemstoneIds::add);
        if (gemstoneIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<Gemstone> gemstones = gemstoneRepository.findAllById(gemstoneIds);
        Map<Long, Gemstone> map = new HashMap<>();
        for (Gemstone gemstone : gemstones) {
            map.put(gemstone.getId(), gemstone);
        }
        return map;
    }

    private HomepagePromotionResponse.PromotionContent buildContent(HomepagePromotion promotion, Gemstone gemstone) {
        String badgeLabel = firstNonBlank(
                promotion.getBadgeLabel(),
                gemstone != null && Boolean.TRUE.equals(gemstone.getIsPremiumBoosted()) ? "Premium Boost" : "Certified"
        );

        String title = firstNonBlank(promotion.getTitle(), gemstone != null ? gemstone.getName() : "Featured Listing");
        String subtitle = firstNonBlank(promotion.getSubtitle(),
                gemstone != null ? gemstone.getOrigin() : "Sri Lanka");

        String priceText = firstNonBlank(
                promotion.getPriceText(),
                gemstone != null && gemstone.getPrice() != null ? lkrFormatter.format(gemstone.getPrice()) : "Price on request"
        );

        String footnoteLeft = firstNonBlank(
                promotion.getFootnoteLeft(),
                gemstone != null && StringUtils.hasText(gemstone.getCertification())
                        ? "Documents · " + gemstone.getCertification()
                        : "Documents · Pending"
        );

        String footnoteRight = firstNonBlank(
                promotion.getFootnoteRight(),
                gemstone != null && gemstone.getViewsCount() != null
                        ? "Views · " + gemstone.getViewsCount()
                        : "Views · —"
        );

        return new HomepagePromotionResponse.PromotionContent(
                badgeLabel,
                promotion.getBadgeStyle(),
                title,
                subtitle,
                priceText,
                footnoteLeft,
                footnoteRight
        );
    }

    private HomepagePromotionResponse.GemstoneSummary buildGemstoneSummary(Gemstone gemstone) {
        return new HomepagePromotionResponse.GemstoneSummary(
                gemstone.getId(),
                gemstone.getName(),
                gemstone.getGemType(),
                gemstone.getCaratWeight(),
                gemstone.getOrigin(),
                gemstone.getCertification(),
                gemstone.getPrice(),
                gemstone.getImageUrl(),
                gemstone.getIsPremiumBoosted()
        );
    }

    private String firstNonBlank(String candidate, String fallback) {
        return StringUtils.hasText(candidate) ? candidate : fallback;
    }

    private String trimmed(String value) {
        return value != null ? value.trim() : null;
    }
}

