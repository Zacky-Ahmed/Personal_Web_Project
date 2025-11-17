package gem.gembro.service;

import gem.gembro.model.EmailVerification;
import gem.gembro.repository.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailVerificationService {

    private static final int CODE_EXPIRY_MINUTES = 10;
    private static final int RESEND_COOLDOWN_SECONDS = 60;

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @Autowired
    private EmailService emailService;

    private final Random random = new Random();

    public void sendVerificationCode(String email) {
        EmailVerification verification = emailVerificationRepository.findByEmail(email)
                .orElse(new EmailVerification());

        if (verification.getLastSentAt() != null &&
                verification.getLastSentAt().isAfter(LocalDateTime.now().minusSeconds(RESEND_COOLDOWN_SECONDS))) {
            throw new RuntimeException("Please wait a moment before requesting a new code.");
        }

        String code = String.format("%04d", random.nextInt(10000));

        verification.setEmail(email);
        verification.setCode(code);
        verification.setVerified(false);
        verification.setExpiresAt(LocalDateTime.now().plusMinutes(CODE_EXPIRY_MINUTES));
        verification.setLastSentAt(LocalDateTime.now());

        emailVerificationRepository.save(verification);

        String subject = "Your GemBro verification code";
        String body = "Hi,\n\n"
                + "Your GemBro verification code is: " + code + "\n"
                + "This code will expire in " + CODE_EXPIRY_MINUTES + " minutes.\n\n"
                + "If you did not request this, you can safely ignore this email.\n\n"
                + "Thank you,\nGemBro Team";

        emailService.sendEmail(email, subject, body);
    }

    public boolean verifyCode(String email, String code) {
        Optional<EmailVerification> verificationOpt = emailVerificationRepository.findByEmail(email);
        if (verificationOpt.isEmpty()) {
            return false;
        }

        EmailVerification verification = verificationOpt.get();
        if (verification.getExpiresAt() == null || verification.getExpiresAt().isBefore(LocalDateTime.now())) {
            return false;
        }

        boolean matches = verification.getCode().equals(code);
        if (matches) {
            verification.setVerified(true);
            emailVerificationRepository.save(verification);
        }
        return matches;
    }

    public boolean isEmailVerified(String email) {
        return emailVerificationRepository.findByEmail(email)
                .map(EmailVerification::isVerified)
                .orElse(false);
    }

    @Transactional
    public void clearVerification(String email) {
        emailVerificationRepository.deleteByEmail(email);
    }
}

