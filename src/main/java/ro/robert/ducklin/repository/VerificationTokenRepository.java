package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.VerificationTokenModel;

import java.util.Optional;

/**
 * Repository for verification token
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenModel, Long> {
    /**
     * Find token in database
     *
     * @param token
     * @return an Optional of VerificationTokenModel if found; empty instead
     */
    Optional<VerificationTokenModel> findByToken(String token);

    /**
     * Delete Token
     * @param token to be deleted
     */
    void deleteVerificationTokenModelByToken(String token);
}
