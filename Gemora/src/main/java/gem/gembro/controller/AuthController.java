package gem.gembro.controller;

import gem.gembro.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/send-verification-code")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.getOrDefault("email", "").trim();

        if (email.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        try {
            emailVerificationService.sendVerificationCode(email);
            Map<String, String> response = new HashMap<>();
            response.put("success", "Verification code sent");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.getOrDefault("email", "").trim();
        String code = request.getOrDefault("code", "").trim();

        if (email.isEmpty() || code.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email and code are required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        boolean isValid = emailVerificationService.verifyCode(email, code);
        if (!isValid) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid or expired verification code");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
}

