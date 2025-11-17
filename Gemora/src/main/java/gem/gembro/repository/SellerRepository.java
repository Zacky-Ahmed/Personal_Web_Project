package gem.gembro.repository;

import gem.gembro.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUserId(Long userId);
    List<Seller> findByVerificationStatus(Seller.VerificationStatus status);
    boolean existsByNicNumber(String nicNumber);
    boolean existsByGemRegNumber(String gemRegNumber);
}

