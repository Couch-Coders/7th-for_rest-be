package couch.forrest.domain.place.dao;

import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @Test
    @Transactional
    public void findPlaceList() {
        //given
        Place place = Place.builder()
                .name("롯데월드")
                .category("테마파크")
                .region1("서울")
                .region2("송파구")
                .build();
        placeRepository.save(place);

        //when
        PlaceRequestDto dto = new PlaceRequestDto("테마파크", "서울", "송파구");
        List<Place> places = placeRepository
                .findAllByCategoryAndRegion1AndRegion2(
                        dto.getCategory(),
                        dto.getRegion1(),
                        dto.getRegion2());
        //then
        Assertions.assertThat(places.get(0).getName()).isEqualTo("롯데월드");
    }

}