package couch.forrest.domain.place.dao;

import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>,placeRepositoryCustom {

    Page<Place> findAllByCategoryAndRegion1AndRegion2In(Pageable pageable,String category, String region1, String[] region2);

    Page<Place> findAllByCategoryAndRegion1(Pageable pageable,String category, String region1);

    @Modifying
    @Query("update Place a set a.likeCount = a.likeCount + 1 where a.id = :id")
    int plusLoveCount(Long id);

    @Modifying
    @Query("update Place a set a.likeCount = a.likeCount - 1 where a.id = :id")
    int minusLoveCount(Long id);


    Optional<Place> findByName(String name);
}
