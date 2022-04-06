package couch.forrest.domain.love.dao;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.love.entity.Love;
import couch.forrest.domain.love.entity.QLove;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.entity.QPlace;
import couch.forrest.domain.review.entity.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoveRepositoryImpl implements LoveRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<Love> findByAverageRatingQuerydsl(Double averageRating) {

        QLove love = QLove.love;
        QPlace place = QPlace.place;

        return queryFactory
                .selectFrom(love)
                .join(love.place, place)
                .where(place.averageRating.gt(averageRating))
                .fetch();
    }
}
