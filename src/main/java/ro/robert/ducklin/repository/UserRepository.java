package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.UserModel;

import javax.validation.constraints.NotBlank;
import java.rmi.server.UID;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UID> {

    /**
     * Find a user by email and password
     * @param password
     * @param email
     * @return a user
     */
    Optional<UserModel> findUserModelByPasswordAndEmail(@NotBlank String password, @NotBlank String email);
}
