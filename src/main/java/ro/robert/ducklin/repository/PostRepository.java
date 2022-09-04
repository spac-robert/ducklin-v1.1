package ro.robert.ducklin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.ducklin.model.PostModel;
/**
 * Repository for posts
 */
@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {
}
