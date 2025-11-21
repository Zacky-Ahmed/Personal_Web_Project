package gem.gembro.repository;

import gem.gembro.model.HomepagePromotion;
import gem.gembro.model.HomepagePromotion.Slot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomepagePromotionRepository extends JpaRepository<HomepagePromotion, Long> {

    Optional<HomepagePromotion> findBySlot(Slot slot);

    List<HomepagePromotion> findAllByActiveTrue(Sort sort);

    List<HomepagePromotion> findAll(Sort sort);
}

