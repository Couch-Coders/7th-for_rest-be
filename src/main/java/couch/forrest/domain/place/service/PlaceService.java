package couch.forrest.domain.place.service;


import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.exception.place.NotFoundPlaceException;
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
        String[] region2Arr = dto.getRegion_2().split("-");


        Page<PlaceListResponseDto> placeList;

        // region2를 고를 때 전체 선택시
        if(dto.getRegion_2().equals(""))
        {
            placeList = placeRepository.
                    findAllByCategoryAndRegion1(
                            pageable,
                            dto.getCategory(),
                            dto.getRegion_1()).map(PlaceListResponseDto::toDto);
        }
        else
        {
            placeList = placeRepository.
                    findAllByCategoryAndRegion1AndRegion2In(
                            pageable,
                            dto.getCategory(),
                            dto.getRegion_1(),
                            region2Arr).map(PlaceListResponseDto::toDto);
        }

        return placeList;
    }

    public void replaceEmptyToNullInPlaceTable(Long id) {
        try {
            Place place = placeRepository.findById(id).orElseThrow(() -> new NotFoundPlaceException("존재하지 않는 place_id 입니다. place_id : " + id));
            place.EmptyToNull();
        } catch (Exception e) {
            System.out.println("e = " + e);
            System.out.println("오류난 플레이스 id = " + id);
        }


    }





}
