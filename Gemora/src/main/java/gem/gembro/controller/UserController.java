package gem.gembro.controller;

import gem.gembro.model.User;
import gem.gembro.service.EmailVerificationService;
import gem.gembro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            // Validate required fields
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Email is required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Password is required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "An error occurred while creating the account: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email and password are required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        User user = userService.getUserByEmail(email).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        if (Boolean.TRUE.equals(user.getPasswordResetRequired())) {
            try {
                emailVerificationService.sendVerificationCode(email);
            } catch (RuntimeException e) {
                // ignore cooldown errors, user will still use previous code
            }
            Map<String, Object> response = new HashMap<>();
            response.put("requirePasswordReset", true);
            response.put("message", "We sent a verification code to " + email + ". Enter it below to set a new password.");
            return ResponseEntity.ok(response);
        }

        user.setPassword(null);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/complete-password-reset")
    public ResponseEntity<?> completePasswordReset(@RequestBody Map<String, String> payload) {
        String email = payload.getOrDefault("email", "").trim();
        String code = payload.getOrDefault("code", "").trim();
        String newPassword = payload.getOrDefault("newPassword", "").trim();

        if (email.isEmpty() || code.isEmpty() || newPassword.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email, verification code, and new password are required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (newPassword.length() < 6) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "New password must be at least 6 characters.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (!emailVerificationService.verifyCode(email, code)) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid or expired verification code.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        userService.completePasswordReset(user, newPassword);
        emailVerificationService.clearVerification(email);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Password updated. You can now sign in.");
        return ResponseEntity.ok(response);
    }
}

