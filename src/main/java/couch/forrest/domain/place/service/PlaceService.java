package couch.forrest.domain.place.service;


import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PlaceService {

    private final PlaceRepository placeRepository;


    public Optional<Place> findOne(Long placeId) {
        return placeRepository.findById(placeId);
    }

    public Page<PlaceListResponseDto> findPlaceList(PlaceRequestDto dto, Pageable pageable) {
        String[] region2Arr = dto.getRegion2().split("-");
        return placeRepository.
                findAllByCategoryAndRegion1AndRegion2In(
                        pageable,
                        dto.getCategory(),
                        dto.getRegion1(),
                        region2Arr).map(PlaceListResponseDto::toDto);
    }





}
