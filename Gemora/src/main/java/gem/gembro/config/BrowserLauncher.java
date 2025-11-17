package gem.gembro.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            // Wait a moment for the server to fully start
            Thread.sleep(1000);
            
            // Get the server port from the application properties
            String port = event.getApplicationContext().getEnvironment().getProperty("server.port", "8080");
            String url = "http://localhost:" + port;
            
            // Open the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Opening browser at: " + url);
            } else {
                // Fallback for systems that don't support Desktop API
                String os = System.getProperty("os.name").toLowerCase();
                Runtime runtime = Runtime.getRuntime();
                
                if (os.contains("win")) {
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac")) {
                    runtime.exec("open " + url);
                } else if (os.contains("nix") || os.contains("nux")) {
                    runtime.exec("xdg-open " + url);
                }
                System.out.println("Opening browser at: " + url);
            }
        } catch (Exception e) {
            System.err.println("Could not open browser automatically: " + e.getMessage());
            System.out.println("Please open your browser and navigate to: http://localhost:8080");
        }
    }
}

