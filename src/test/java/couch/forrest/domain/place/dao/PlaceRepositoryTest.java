package couch.forrest.domain.place.dao;

import couch.forrest.domain.place.entity.Place;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;
    
    /*@Test
    public void save() throws Exception {
        //given
        Place place = Place.builder()
                .name("디아트스페이스 193")
                .category("전망대")
                .region1("대전")
                .region2("유성구")
                .address("대전 유성구 엑스포로 1")
                .build();

        //when
        Place savedPlace = placeRepository.save(place);

        //then
        assertThat(savedPlace.getName()).isEqualTo("디아트스페이스 193");
        assertThat(savedPlace.getCategory()).isEqualTo("전망대");
        assertThat(savedPlace.getRegion1()).isEqualTo("대전");

    }*/

}