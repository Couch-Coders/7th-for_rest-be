package couch.forrest.domain.love.service;

import couch.forrest.domain.love.dao.LoveRepository;
import couch.forrest.domain.love.entity.Love;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class LoveService {
    private final LoveRepository loveRepository;
    private final PlaceRepository PlaceRepository;
    private long loveCount;

    // 좋아요를 누를 시 실행
    public Long lovePlace(Member member, Long PlaceId)
    {
        Place place = Place.builder()
                .id(PlaceId)
                .build();

        Optional<Place> placeResult = PlaceRepository.findById(PlaceId);
        if(placeResult.isEmpty())
        {
            throw new IllegalArgumentException("해당 장소는 존재하지 않습니다. id="+PlaceId);
        }
        // 좋아요를 이미 누른 상태인지 확인
        Optional<Love> result = loveRepository.findByMemberAndPlace(member,place);

        // 좋아요를 처음 누른 경우
        if(!result.isPresent())
        {
            Love love = Love.builder()
                    .member(member)
                    .place(place)
                    .build();

            // 좋아요를 증가 시킴
            PlaceRepository.plusLoveCount(PlaceId);
            loveRepository.save(love);
            if(placeResult.get().getLikeCount() == null)
                loveCount = 0;
            else
                loveCount = placeResult.get().getLikeCount() +1L;

            return loveCount;

        }
        // 좋아요를 이미 누른 경우 좋아요를 해제
        else{
            // Place의 likeCount 값 에서 1을 빼준다.
            PlaceRepository.minusLoveCount(PlaceId);
            Love love = result.get();
            loveRepository.delete(love);
            if(placeResult.get().getLikeCount() == null)
                loveCount = 0;
            else
                loveCount = placeResult.get().getLikeCount() - 1L;

            return loveCount;
        }


    }

    public Map<String, Boolean> checkPlace(Member member, Long placeId) {
        Place place = Place.builder()
                .id(placeId)
                .build();
        Optional<Love> love = loveRepository.findByMemberAndPlace(member, place);

        Optional<Place> placeResult = PlaceRepository.findById(placeId);
        if(placeResult.isEmpty())
        {
            throw new IllegalArgumentException("해당 장소는 존재하지 않습니다. id="+placeId);
        }

        Map<String, Boolean> map = new HashMap<>();
        if(love.isPresent())
            map.put("isLove", true);
        else
            map.put("isLove", false);
        return map;
    }
}
