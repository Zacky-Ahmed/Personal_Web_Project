package gem.gembro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "nic_number")
    private String nicNumber;

    @Column(name = "gem_reg_number")
    private String gemRegNumber;

    @Column(name = "nic_file_path")
    private String nicFilePath;

    @Column(name = "gem_reg_file_path")
    private String gemRegFilePath;

    @Column(name = "address")
    private String address;

    @Column(name = "verification_status")
    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "subscription_plan")
    @Enumerated(EnumType.STRING)
    private SubscriptionPlan subscriptionPlan = SubscriptionPlan.FREE;

    @Column(name = "premium_boosts_left")
    private Integer premiumBoostsLeft = 0;

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

    public enum VerificationStatus {
        PENDING, APPROVED, REJECTED
    }

    public enum SubscriptionPlan {
        FREE, PREMIUM, GROWTH, ELITE
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getGemRegNumber() {
        return gemRegNumber;
    }

    public void setGemRegNumber(String gemRegNumber) {
        this.gemRegNumber = gemRegNumber;
    }

    public String getNicFilePath() {
        return nicFilePath;
    }

    public void setNicFilePath(String nicFilePath) {
        this.nicFilePath = nicFilePath;
    }

    public String getGemRegFilePath() {
        return gemRegFilePath;
    }

    public void setGemRegFilePath(String gemRegFilePath) {
        this.gemRegFilePath = gemRegFilePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public Integer getPremiumBoostsLeft() {
        return premiumBoostsLeft;
    }

    public void setPremiumBoostsLeft(Integer premiumBoostsLeft) {
        this.premiumBoostsLeft = premiumBoostsLeft;
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

