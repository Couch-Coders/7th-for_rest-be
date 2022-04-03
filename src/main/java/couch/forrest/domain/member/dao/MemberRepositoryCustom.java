package couch.forrest.domain.member.dao;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;

import java.util.List;

// QueryDsl로 작성할 쿼리는 이 곳에 시그니처를 선언하고
// '~RepositoryImpl'에서 구현한다.
public interface MemberRepositoryCustom {
    // 내가 좋아요를 누른 관광명소 가져오기
    List<Place> findMyFavoritePlaces(Long memberId, String region1, String region2, String category);
}
