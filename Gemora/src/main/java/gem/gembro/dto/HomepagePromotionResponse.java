package gem.gembro.dto;

import gem.gembro.model.HomepagePromotion;

import java.math.BigDecimal;

public class HomepagePromotionResponse {

    private Long id;
    private HomepagePromotion.Slot slot;
    private boolean active;
    private PromotionContent content;
    private GemstoneSummary gemstone;

    public HomepagePromotionResponse(Long id,
                                     HomepagePromotion.Slot slot,
                                     boolean active,
                                     PromotionContent content,
                                     GemstoneSummary gemstone) {
        this.id = id;
        this.slot = slot;
        this.active = active;
        this.content = content;
        this.gemstone = gemstone;
    }

    public Long getId() {
        return id;
    }

    public HomepagePromotion.Slot getSlot() {
        return slot;
    }

    public boolean isActive() {
        return active;
    }

    public PromotionContent getContent() {
        return content;
    }

    public GemstoneSummary getGemstone() {
        return gemstone;
    }

    public static class PromotionContent {
        private String badgeLabel;
        private String badgeStyle;
        private String title;
        private String subtitle;
        private String priceText;
        private String footnoteLeft;
        private String footnoteRight;

        public PromotionContent(String badgeLabel,
                                String badgeStyle,
                                String title,
                                String subtitle,
                                String priceText,
                                String footnoteLeft,
                                String footnoteRight) {
            this.badgeLabel = badgeLabel;
            this.badgeStyle = badgeStyle;
            this.title = title;
            this.subtitle = subtitle;
            this.priceText = priceText;
            this.footnoteLeft = footnoteLeft;
            this.footnoteRight = footnoteRight;
        }

        public String getBadgeLabel() {
            return badgeLabel;
        }

        public String getBadgeStyle() {
            return badgeStyle;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getPriceText() {
            return priceText;
        }

        public String getFootnoteLeft() {
            return footnoteLeft;
        }

        public String getFootnoteRight() {
            return footnoteRight;
        }
    }

    public static class GemstoneSummary {
        private Long id;
        private String name;
        private String gemType;
        private BigDecimal caratWeight;
        private String origin;
        private String certification;
        private BigDecimal price;
        private String imageUrl;
        private Boolean premiumBoosted;

        public GemstoneSummary(Long id,
                               String name,
                               String gemType,
                               BigDecimal caratWeight,
                               String origin,
                               String certification,
                               BigDecimal price,
                               String imageUrl,
                               Boolean premiumBoosted) {
            this.id = id;
            this.name = name;
            this.gemType = gemType;
            this.caratWeight = caratWeight;
            this.origin = origin;
            this.certification = certification;
            this.price = price;
            this.imageUrl = imageUrl;
            this.premiumBoosted = premiumBoosted;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getGemType() {
            return gemType;
        }

        public BigDecimal getCaratWeight() {
            return caratWeight;
        }

        public String getOrigin() {
            return origin;
        }

        public String getCertification() {
            return certification;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public Boolean getPremiumBoosted() {
            return premiumBoosted;
        }
    }
}

