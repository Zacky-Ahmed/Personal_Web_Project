package gem.gembro.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "homepage_promotions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slot")
})
public class HomepagePromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Slot slot;

    @Column(name = "gemstone_id", nullable = false)
    private Long gemstoneId;

    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "subtitle", length = 150)
    private String subtitle;

    @Column(name = "badge_label", length = 80)
    private String badgeLabel;

    @Column(name = "badge_style", length = 40)
    private String badgeStyle;

    @Column(name = "price_text", length = 120)
    private String priceText;

    @Column(name = "footnote_left", length = 160)
    private String footnoteLeft;

    @Column(name = "footnote_right", length = 160)
    private String footnoteRight;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Slot {
        HERO_PRIMARY,
        HERO_SECONDARY,
        FEATURED_ONE,
        FEATURED_TWO,
        FEATURED_THREE
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

