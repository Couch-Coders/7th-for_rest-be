package couch.forrest.domain.member.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.love.entity.QLove;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.member.entity.QMember;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceQResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.entity.QPlace;
import couch.forrest.domain.review.dto.response.ReviewQDto;
import couch.forrest.domain.review.entity.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.jpa.JPAExpressions.select;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PlaceQResponseDto> findMyFavoritePlaces(Long memberId,Pageable pageable) {

        QMember member = QMember.member;
        QLove love = QLove.love;
        QLove subLove = new QLove("subLove");
        QPlace place = QPlace.place;
        QReview review = QReview.review;

        QueryResults<PlaceQResponseDto> results = queryFactory
                .select(Projections.fields(
                        PlaceQResponseDto.class,
                        place.id,
                        place.name,
                        place.address,
                        place.image.as("img_src"),
                        place.tag,
                        place.likeCount.as("like_count"),
                        place.phone,
                        place.category,
                        place.region1.as("region_1"),
                        review.reviewRating.avg().as("avg"),
                        review.id.count().as("review_count")
                )).from(review)
                .leftJoin(place)
                .on(review.place.eq(place))
                .where(place.in(
                                     select(subLove.place)
                                        .from(subLove)
                                        .where(subLove.member.id.eq(memberId))
                        )
                )
                .groupBy(place.id, place.name, place.address, place.image, place.tag,place.likeCount)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<PlaceQResponseDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

}
