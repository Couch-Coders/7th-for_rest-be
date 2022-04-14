package couch.forrest.domain.place.service;


import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PlaceService {

    private final PlaceRepository placeRepository;


    public Optional<Place> findOne(Long placeId) {
        return placeRepository.findById(placeId);
    }

    public List<Place> findPlaceList(PlaceRequestDto dto) {
        List<Place> places = placeRepository.
                findAllByCategoryAndRegion1AndRegion2(
                        dto.getCategory(),
                        dto.getRegion1(),
                        dto.getRegion2());
        return places;
    }





}
