package couch.forrest.domain.place.service;

import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlaceServiceTest {


    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceService placeService;

    @Test
    @Transactional
    public void 장소_리스트_반환() throws Exception {
        //given
        Place place = Place.builder()
                .name("롯데월드")
                .category("테마파크")
                .region1("서울")
                .region2("송파구")
                .build();
        placeRepository.save(place);

        place = Place.builder()
                .name("송파랜드")
                .category("테마파크")
                .region1("서울")
                .region2("송파구")
                .build();
        placeRepository.save(place);

        //when
        PlaceRequestDto dto = new PlaceRequestDto("테마파크", "서울", "송파구");
        List<Place> placeList = placeService.findPlaceList(dto);

        //then
        Assertions.assertThat(placeList.size()).isEqualTo(2);

    }




}