package couch.forrest.domain.love.dao;

import couch.forrest.domain.love.entity.Love;

import java.util.List;

public interface LoveRepositoryCustom {

    //좋아요를 누른 장소 중에서 평균 리뷰 점수가 파라미터로 넘어오는 averageRating값 이상인 장소들만 love 리스트를 반환
    List<Love> findByAverageRatingQuerydsl(Double averageRating);
}
