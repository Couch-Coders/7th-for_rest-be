package couch.forrest.controller;


import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import couch.forrest.exception.CustomException;
import couch.forrest.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Place API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;



    @Parameter(name = "placeId",required = true)
    @Operation(description = "place 상세 정보 조회")
    @GetMapping("{placeId}")
    public PlaceResponseDto placeDetail(@PathVariable("placeId") Long placeId) {
        Place place = placeService.findOne(placeId)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NOT_FOUND_PLACE, "존재하지 않는 place id :" + placeId);
                });

        return PlaceResponseDto.toDto(place);
    }


    @Operation(description = "place 리스트 조회")
    @GetMapping(value = "list")
    public Page<PlaceListResponseDto> getPlaceList(@ModelAttribute PlaceRequestDto dto, Pageable pageable) {
        return placeService.findPlaceList(dto,pageable);
    }
}
