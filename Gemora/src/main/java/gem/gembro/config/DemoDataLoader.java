package gem.gembro.config;

import gem.gembro.model.Gemstone;
import gem.gembro.model.Seller;
import gem.gembro.model.User;
import gem.gembro.repository.GemstoneRepository;
import gem.gembro.repository.SellerRepository;
import gem.gembro.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoDataLoader.class);
    private static final String DEMO_EMAIL = "demo.seller@gembro.app";
    private static final String DEMO_PASSWORD = "DemoSeller123!";
    private static final String TEST_SELLER_EMAIL = "test.seller@gembro.app";
    private static final String TEST_SELLER_PASSWORD = "TestSeller123!";

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final GemstoneRepository gemstoneRepository;
    private final PasswordEncoder passwordEncoder;

    public DemoDataLoader(UserRepository userRepository,
                          SellerRepository sellerRepository,
                          GemstoneRepository gemstoneRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.gemstoneRepository = gemstoneRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User demoUser = userRepository.findByEmail(DEMO_EMAIL).orElseGet(() -> {
            User user = new User();
            user.setEmail(DEMO_EMAIL);
            user.setFullName("Demo Seller");
            user.setPhoneNumber("+94 77 555 1234");
            user.setPassword(passwordEncoder.encode(DEMO_PASSWORD));
            User saved = userRepository.save(user);
            logger.info("Created demo seller user with email {}", DEMO_EMAIL);
            return saved;
        });

        if (!passwordEncoder.matches(DEMO_PASSWORD, demoUser.getPassword())) {
            demoUser.setPassword(passwordEncoder.encode(DEMO_PASSWORD));
            userRepository.save(demoUser);
            logger.info("Reset password for demo seller {}", DEMO_EMAIL);
        }

        sellerRepository.findByUserId(demoUser.getId()).orElseGet(() -> {
            Seller seller = new Seller();
            seller.setUserId(demoUser.getId());
            seller.setBusinessName("GemBro Demo Traders");
            seller.setNicNumber("863254789V");
            seller.setGemRegNumber("GEM/DEMO/001");
            seller.setAddress("123 Demo Lane, Colombo, Sri Lanka");
            seller.setVerificationStatus(Seller.VerificationStatus.APPROVED);
            seller.setSubscriptionPlan(Seller.SubscriptionPlan.PREMIUM);
            seller.setPremiumBoostsLeft(3);
            Seller savedSeller = sellerRepository.save(seller);
            logger.info("Created demo seller profile for {}", DEMO_EMAIL);
            return savedSeller;
        });

        // Create test seller account
        User testUser = userRepository.findByEmail(TEST_SELLER_EMAIL).orElseGet(() -> {
            User user = new User();
            user.setEmail(TEST_SELLER_EMAIL);
            user.setFullName("Test Seller");
            user.setPhoneNumber("+94 77 999 8888");
            user.setPassword(passwordEncoder.encode(TEST_SELLER_PASSWORD));
            User saved = userRepository.save(user);
            logger.info("Created test seller user with email {}", TEST_SELLER_EMAIL);
            return saved;
        });

        if (!passwordEncoder.matches(TEST_SELLER_PASSWORD, testUser.getPassword())) {
            testUser.setPassword(passwordEncoder.encode(TEST_SELLER_PASSWORD));
            userRepository.save(testUser);
            logger.info("Reset password for test seller {}", TEST_SELLER_EMAIL);
        }

        Seller testSeller = sellerRepository.findByUserId(testUser.getId()).orElseGet(() -> {
            Seller seller = new Seller();
            seller.setUserId(testUser.getId());
            seller.setBusinessName("Test Gem Traders");
            seller.setNicNumber("987654321V");
            seller.setGemRegNumber("GEM/TEST/001");
            seller.setAddress("456 Test Street, Colombo, Sri Lanka");
            seller.setVerificationStatus(Seller.VerificationStatus.APPROVED);
            seller.setSubscriptionPlan(Seller.SubscriptionPlan.FREE);
            seller.setPremiumBoostsLeft(5);
            Seller savedSeller = sellerRepository.save(seller);
            logger.info("Created test seller profile for {}", TEST_SELLER_EMAIL);
            return savedSeller;
        });

        // Ensure test seller has at least two demo gemstone listings
        if (gemstoneRepository.findBySellerId(testSeller.getId()).isEmpty()) {
            Gemstone blueSapphire = new Gemstone();
            blueSapphire.setSellerId(testSeller.getId());
            blueSapphire.setName("Royal Blue Ceylon Sapphire");
            blueSapphire.setGemType("Sapphire");
            blueSapphire.setCaratWeight(new java.math.BigDecimal("3.25"));
            blueSapphire.setCutType("Oval");
            blueSapphire.setColor("Royal Blue");
            blueSapphire.setClarity("VVS1");
            blueSapphire.setOrigin("Sri Lanka");
            blueSapphire.setCertification("GemReg");
            blueSapphire.setCertificateNumber("GEMREG-TEST-001");
            blueSapphire.setPrice(new java.math.BigDecimal("2500.00"));
            blueSapphire.setDescription("Premium Ceylon sapphire with exceptional color saturation and brilliance. Ideal for high-end jewellery.");
            blueSapphire.setImageUrl("/images/gems/gem-01.jpg");
            gemstoneRepository.save(blueSapphire);

            Gemstone pinkSapphire = new Gemstone();
            pinkSapphire.setSellerId(testSeller.getId());
            pinkSapphire.setName("Pastel Pink Sapphire");
            pinkSapphire.setGemType("Sapphire");
            pinkSapphire.setCaratWeight(new java.math.BigDecimal("2.10"));
            pinkSapphire.setCutType("Cushion");
            pinkSapphire.setColor("Pastel Pink");
            pinkSapphire.setClarity("VS1");
            pinkSapphire.setOrigin("Sri Lanka");
            pinkSapphire.setCertification("GemReg");
            pinkSapphire.setCertificateNumber("GEMREG-TEST-002");
            pinkSapphire.setPrice(new java.math.BigDecimal("1450.00"));
            pinkSapphire.setDescription("Delicate pastel pink sapphire with excellent fire and symmetry. Perfect for engagement rings.");
            pinkSapphire.setImageUrl("/images/gems/gem-02.jpg");
            gemstoneRepository.save(pinkSapphire);

            logger.info("Created demo gemstones for test seller {}", TEST_SELLER_EMAIL);
            logger.info("=== TEST SELLER ACCOUNT CREATED ===");
            logger.info("Email: {}", TEST_SELLER_EMAIL);
            logger.info("Password: {}", TEST_SELLER_PASSWORD);
            logger.info("===================================");
        } else {
            logger.info("Test seller {} already has gemstone listings. Skipping demo gemstone creation.", TEST_SELLER_EMAIL);
        }
    }
}


