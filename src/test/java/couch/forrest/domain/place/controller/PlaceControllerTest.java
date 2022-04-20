package couch.forrest.domain.place.controller;

import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class PlaceControllerTest {

    @Autowired
    PlaceService placeService;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceController placeController;


    @Test
    public void 리스트_반환_API() throws Exception {
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
//        List<PlaceListResponseDto> placeDtoList = placeController.getPlaceList(dto.);


        //then
//        Assertions.assertThat(placeDtoList.size()).isEqualTo(2);
//        Assertions.assertThat(placeDtoList.get(0).getName()).isEqualTo("롯데월드");
    }
}