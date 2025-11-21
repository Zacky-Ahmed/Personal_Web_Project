package gem.gembro.service;

import gem.gembro.model.Seller;
import gem.gembro.model.SellerApplication;
import gem.gembro.model.SellerApplication.ApplicationStatus;
import gem.gembro.model.User;
import gem.gembro.repository.SellerApplicationRepository;
import gem.gembro.repository.SellerRepository;
import gem.gembro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

@Service
public class SellerApplicationService {

    private final SellerApplicationRepository applicationRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final SecureRandom secureRandom = new SecureRandom();

    public SellerApplicationService(SellerApplicationRepository applicationRepository,
                                    SellerRepository sellerRepository,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    EmailService emailService) {
        this.applicationRepository = applicationRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public SellerApplication submitApplication(SellerApplication application) {
        application.setId(null);
        application.setStatus(ApplicationStatus.PENDING);
        application.setProcessedAt(null);
        application.setProcessedBy(null);
        return applicationRepository.save(application);
    }

    public List<SellerApplication> getApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status);
    }

    public SellerApplication updateStatus(Long id, ApplicationStatus status, Long adminId) {
        SellerApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        application.setStatus(status);
        application.setProcessedBy(adminId);
        application.setProcessedAt(LocalDateTime.now());

        if (status == ApplicationStatus.APPROVED) {
            handleApproval(application);
        }

        return applicationRepository.save(application);
    }

    private void handleApproval(SellerApplication application) {
        User user = resolveOrCreateUser(application);
        application.setUserId(user.getId());

        Seller seller = sellerRepository.findByUserId(user.getId()).orElseGet(Seller::new);
        seller.setUserId(user.getId());
        seller.setBusinessName(application.getBusinessName());
        seller.setNicNumber(application.getNicNumber());
        seller.setGemRegNumber(application.getGemRegNumber());
        seller.setAddress(application.getAddress());
        seller.setNicFilePath(application.getNicFrontFileName());
        seller.setGemRegFilePath(application.getGemRegFileName());
        seller.setVerificationStatus(Seller.VerificationStatus.APPROVED);
        sellerRepository.save(seller);
    }

    private User resolveOrCreateUser(SellerApplication application) {
        Optional<User> userOpt = Optional.empty();
        if (application.getUserId() != null) {
            userOpt = userRepository.findById(application.getUserId());
        }
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(application.getEmail());
        }

        String tempPassword = generateTempPassword();
        User user = userOpt.orElseGet(User::new);
        user.setEmail(application.getEmail());
        user.setFullName(application.getFullName());
        user.setPhoneNumber(application.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setPasswordResetRequired(true);
        User saved = userRepository.save(user);

        sendApprovalEmail(application, tempPassword);
        return saved;
    }

    private String generateTempPassword() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void sendApprovalEmail(SellerApplication application, String tempPassword) {
        String subject = "Your GemBro seller account is approved";
        String body = "Hi " + Optional.ofNullable(application.getFullName()).orElse("there") + ",\n\n" +
                "Good news! Your seller application has been approved.\n\n" +
                "You can now sign in with:\n" +
                "Email: " + application.getEmail() + "\n" +
                "Temporary password: " + tempPassword + "\n\n" +
                "For security, you'll be asked to enter a verification code and set a new password the first time you log in.\n\n" +
                "Regards,\nGemBro Team";
        emailService.sendEmail(application.getEmail(), subject, body);
    }
}

