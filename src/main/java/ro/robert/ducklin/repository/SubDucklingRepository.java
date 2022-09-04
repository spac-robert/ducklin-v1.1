package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.SubDucklingModel;

/**
 * Repository for subducklings
 */
@Repository
public interface SubDucklingRepository extends JpaRepository<SubDucklingModel, Long> {
}
