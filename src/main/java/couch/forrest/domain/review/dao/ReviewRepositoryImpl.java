package couch.forrest.domain.review.dao;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.entity.QReview;
import couch.forrest.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> loadReview(Long placeId) {
        QReview review = QReview.review;
        Place place = Place.builder()
                .id(placeId)
                .build();


        return queryFactory.selectFrom(review).
        where(review.place.eq(place))
                .fetch();
    }
}
