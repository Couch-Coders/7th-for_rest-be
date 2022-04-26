package couch.forrest.domain.member.dao;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceQResponseDto;
import couch.forrest.domain.place.dto.response.PlaceResponseDto;
import couch.forrest.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// QueryDsl로 작성할 쿼리는 이 곳에 시그니처를 선언하고
// '~RepositoryImpl'에서 구현한다.
public interface MemberRepositoryCustom {
    // 내가 좋아요를 누른 관광명소 가져오기
    Page<PlaceQResponseDto> findMyFavoritePlaces(Long memberId, Pageable pageable);

    List<PlaceQResponseDto> myPageLovedPlaces(Long memberId);
}
