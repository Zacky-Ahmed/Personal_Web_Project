package gem.gembro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HomepagePromotionRequest {

    @NotNull
    private Long gemstoneId;

    @Size(max = 150)
    private String title;

    @Size(max = 150)
    private String subtitle;

    @Size(max = 80)
    private String badgeLabel;

    @Size(max = 40)
    private String badgeStyle;

    @Size(max = 120)
    private String priceText;

    @Size(max = 160)
    private String footnoteLeft;

    @Size(max = 160)
    private String footnoteRight;

    private Boolean active = true;

    public Long getGemstoneId() {
        return gemstoneId;
    }

    public void setGemstoneId(Long gemstoneId) {
        this.gemstoneId = gemstoneId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBadgeLabel() {
        return badgeLabel;
    }

    public void setBadgeLabel(String badgeLabel) {
        this.badgeLabel = badgeLabel;
    }

    public String getBadgeStyle() {
        return badgeStyle;
    }

    public void setBadgeStyle(String badgeStyle) {
        this.badgeStyle = badgeStyle;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getFootnoteLeft() {
        return footnoteLeft;
    }

    public void setFootnoteLeft(String footnoteLeft) {
        this.footnoteLeft = footnoteLeft;
    }

    public String getFootnoteRight() {
        return footnoteRight;
    }

    public void setFootnoteRight(String footnoteRight) {
        this.footnoteRight = footnoteRight;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

