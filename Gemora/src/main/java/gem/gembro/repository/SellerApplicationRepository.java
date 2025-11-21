package gem.gembro.repository;

import gem.gembro.model.SellerApplication;
import gem.gembro.model.SellerApplication.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerApplicationRepository extends JpaRepository<SellerApplication, Long> {

    List<SellerApplication> findByStatus(ApplicationStatus status);
}

