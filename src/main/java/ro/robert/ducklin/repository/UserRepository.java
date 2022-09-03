package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.UserModel;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * Repository for user
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Find a user by email and password
     * @param email
     * @return a user
     */
    Optional<UserModel> findUserModelByEmail(@NotBlank String email);
}
