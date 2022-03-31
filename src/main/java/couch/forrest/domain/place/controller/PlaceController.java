package couch.forrest.domain.place.controller;


import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping("{placeId}")
    public PlaceDto placeDetail(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findOne(placeId).get();

        PlaceDto placeDto = new PlaceDto(place.getName(), place.getAddress(), place.getRegion1(),
                place.getRegion2(), place.getCategory());

        return placeDto;
    }


    @Data
    @AllArgsConstructor
    static class PlaceDto {
        private String name;
        private String address;
        private String region1;
        private String region2;
        private String category;

    }

}
