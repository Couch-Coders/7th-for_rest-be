package couch.forrest.domain.member.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.love.entity.QLove;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.member.entity.QMember;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.entity.QPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PlaceListResponseDto> findMyFavoritePlaces(Long memberId,Pageable pageable) {

        QMember member = QMember.member;
        QLove love = QLove.love;
        QPlace place = QPlace.place;

        List<Place> result = queryFactory
                .selectFrom(place)
                .rightJoin(love)
                .on(place.eq(love.place))
                .rightJoin(member)
                .on(love.member.eq(member))
                .where(love.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PlaceListResponseDto> content = result.stream()
                .map(PlaceListResponseDto::new)
                .collect(Collectors.toList());

        JPAQuery<Place> countQuery = queryFactory
                .selectFrom(place)
                .rightJoin(love)
                .on(place.eq(love.place))
                .rightJoin(member)
                .on(love.member.eq(member))
                .where(love.member.id.eq(memberId));



        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

}
