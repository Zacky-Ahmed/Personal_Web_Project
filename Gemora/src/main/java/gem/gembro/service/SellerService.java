package gem.gembro.service;

import gem.gembro.model.Seller;
import gem.gembro.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller createSeller(Seller seller) {
        if (seller.getNicNumber() != null && sellerRepository.existsByNicNumber(seller.getNicNumber())) {
            throw new RuntimeException("NIC number already registered");
        }
        if (seller.getGemRegNumber() != null && sellerRepository.existsByGemRegNumber(seller.getGemRegNumber())) {
            throw new RuntimeException("GemReg number already registered");
        }
        return sellerRepository.save(seller);
    }

    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public Optional<Seller> getSellerByUserId(Long userId) {
        return sellerRepository.findByUserId(userId);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public List<Seller> getSellersByVerificationStatus(Seller.VerificationStatus status) {
        return sellerRepository.findByVerificationStatus(status);
    }

    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller approveSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        seller.setVerificationStatus(Seller.VerificationStatus.APPROVED);
        return sellerRepository.save(seller);
    }

    public Seller rejectSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        seller.setVerificationStatus(Seller.VerificationStatus.REJECTED);
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}

