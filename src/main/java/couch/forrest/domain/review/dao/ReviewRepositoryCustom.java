package couch.forrest.domain.review.dao;

import couch.forrest.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> loadReview(Long placeId);
}
