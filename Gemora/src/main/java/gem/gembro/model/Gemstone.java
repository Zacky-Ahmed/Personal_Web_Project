package gem.gembro.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gemstones")
public class Gemstone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private String name;

    @Column(name = "gem_type")
    private String gemType; // Sapphire, Ruby, Emerald, etc.

    @Column(name = "carat_weight")
    private BigDecimal caratWeight;

    @Column(name = "cut_type")
    private String cutType; // Oval, Round, Cushion, etc.

    @Column(name = "color")
    private String color;

    @Column(name = "clarity")
    private String clarity; // VVS1, VS1, etc.

    @Column(name = "origin")
    private String origin; // Sri Lanka, Myanmar, etc.

    @Column(name = "certification")
    private String certification; // GIA, GemReg, etc.

    @Column(name = "certificate_number")
    private String certificateNumber;

    @Column(name = "certificate_file_path")
    private String certificateFilePath;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_premium_boosted")
    private Boolean isPremiumBoosted = false;

    @Column(name = "premium_boost_expires_at")
    private LocalDateTime premiumBoostExpiresAt;

    @Column(name = "views_count")
    private Integer viewsCount = 0;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ListingStatus status = ListingStatus.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ListingStatus {
        ACTIVE, SOLD, INACTIVE
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGemType() {
        return gemType;
    }

    public void setGemType(String gemType) {
        this.gemType = gemType;
    }

    public BigDecimal getCaratWeight() {
        return caratWeight;
    }

    public void setCaratWeight(BigDecimal caratWeight) {
        this.caratWeight = caratWeight;
    }

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClarity() {
        return clarity;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateFilePath() {
        return certificateFilePath;
    }

    public void setCertificateFilePath(String certificateFilePath) {
        this.certificateFilePath = certificateFilePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsPremiumBoosted() {
        return isPremiumBoosted;
    }

    public void setIsPremiumBoosted(Boolean isPremiumBoosted) {
        this.isPremiumBoosted = isPremiumBoosted;
    }

    public LocalDateTime getPremiumBoostExpiresAt() {
        return premiumBoostExpiresAt;
    }

    public void setPremiumBoostExpiresAt(LocalDateTime premiumBoostExpiresAt) {
        this.premiumBoostExpiresAt = premiumBoostExpiresAt;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public ListingStatus getStatus() {
        return status;
    }

    public void setStatus(ListingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

