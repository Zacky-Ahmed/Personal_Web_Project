package gem.gembro.repository;

import gem.gembro.model.Gemstone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GemstoneRepository extends JpaRepository<Gemstone, Long> {
    List<Gemstone> findBySellerId(Long sellerId);
    List<Gemstone> findByStatus(Gemstone.ListingStatus status);
    List<Gemstone> findByIsPremiumBoostedTrue();
    List<Gemstone> findByGemType(String gemType);
    List<Gemstone> findByOrigin(String origin);
    
    @Query("SELECT g FROM Gemstone g WHERE g.status = 'ACTIVE' ORDER BY g.isPremiumBoosted DESC, g.createdAt DESC")
    List<Gemstone> findAllActiveOrderedByPremium();
}

