package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.UserModel;

import java.rmi.server.UID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UID> {
}
