package couch.forrest.domain.place.dao;

import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByCategoryAndRegion1AndRegion2(String category,String region1, String region2);


}
