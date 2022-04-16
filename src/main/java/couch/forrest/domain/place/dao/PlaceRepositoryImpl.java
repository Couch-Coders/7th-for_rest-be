package couch.forrest.domain.place.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import couch.forrest.domain.place.dto.request.PlaceRequestDto;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.entity.QPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements placeRepositoryCustom {


    private final JPAQueryFactory queryFactory;


}
