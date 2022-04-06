package couch.forrest.domain.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.love.entity.QLove;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.member.entity.QMember;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.entity.QPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Place> findMyFavoritePlaces(Long memberId, String region1, String region2, String category) {
        QMember member = QMember.member;
        QLove love = QLove.love;
        QPlace place = QPlace.place;

        List<Place> result = queryFactory
                .selectFrom(place)
                .rightJoin(love)
                .on(place.id.eq(love.id))
                .join(member)
                .on(love.id.eq(member.id))
                .where(love.id.eq(memberId))
                .fetch();


        return result;
    }
}
