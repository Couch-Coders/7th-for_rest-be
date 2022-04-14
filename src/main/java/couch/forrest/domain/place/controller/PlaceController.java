package couch.forrest.domain.place.controller;


import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import couch.forrest.exception.place.NotFoundPlaceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping("{placeId}")
    public PlaceResponseDto placeDetail(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findOne(placeId).orElseThrow(() -> new NotFoundPlaceException("존재하지 않는 place_id 입니다. place_id : " + placeId));

        return PlaceResponseDto.toDto(place);
    }

    @GetMapping("list")
    public List<PlaceListResponseDto> getPlaceList(@ModelAttribute PlaceRequestDto dto) {

        List<Place> placeList = placeService.findPlaceList(dto);

        List<PlaceListResponseDto> dtoList = new ArrayList<>();
        for (Place place : placeList) {
            dtoList.add(PlaceListResponseDto.toDto(place));
        }

        return dtoList;
    }
}
