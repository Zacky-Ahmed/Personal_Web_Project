package gem.gembro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index.html")
    public String indexHtml() {
        return "index";
    }

    @GetMapping("/about.html")
    public String about() {
        return "about";
    }

    @GetMapping("/contact.html")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/marketplace.html")
    public String marketplace() {
        return "marketplace";
    }

    @GetMapping("/seller.html")
    public String seller() {
        return "seller";
    }

    @GetMapping("/signup.html")
    public String signup() {
        return "signup";
    }

    @GetMapping("/user-dashboard.html")
    public String userDashboard() {
        return "user-dashboard";
    }

    @GetMapping("/admin-dashboard.html")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin-login.html")
    public String adminLogin() {
        return "admin-login";
    }
}

