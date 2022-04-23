package couch.forrest.domain.review.dao;

import couch.forrest.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewRepositoryCustom {
    Optional<Review> findByName(String name);
}
