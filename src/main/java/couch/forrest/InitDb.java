package couch.forrest;


import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    private final PlaceRepository placeRepository;

    private final PlaceService placeService;


//    @PostConstruct
    public void init() {
        CSVReader csvReader = new CSVReader();
        List<List<String>> lists = csvReader.readCSV();
        List<Place> places = new ArrayList<>();
        int i = 0;
        for (List<String> list : lists) {
            i++;
            if (i > 100) {
                break;
            }
            System.out.println("list.size() = " + list.size());
            String placename = list.get(0);
            String category = list.get(1);
            String region_1 = list.get(2);
            String region_2 = list.get(3);
            String address = list.get(4);
            String contact = list.get(5);
            String placeinfo = list.get(6);
            String openhours = list.get(7);
            String cost = list.get(8);
            String tag = list.get(9);
            String wayinfo = list.get(10);
            String img_src = list.get(11);
            String link_url = list.get(12);

            Double average_rating = 0.0;

            Double latitude = 0.0;
            Double longitude = 0.0;

            Long viewCount = Long.valueOf(0);
            Long likeCount = Long.valueOf(0);


            Place place = Place.builder()
                    .name(placename)
                    .category(category)
                    .region1(region_1)
                    .region2(region_2)
                    .address(address)
                    .phone(contact)
                    .placeinfo(placeinfo)
                    .operatingHours(openhours)
                    .cost(cost)
                    .tag(tag)
                    .wayinfo(wayinfo)
                    .image(img_src)
                    .link_url(link_url)
                    .averageRating(average_rating)
                    .viewCount(viewCount)
                    .likeCount(likeCount)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();

            places.add(place);
        }

        initService.dbInit(places);
    }


//    @PostConstruct
    public void replaceEmptytoNull() {


        for (int i = 1; i < 7875; i++) {
            Long placeId = Long.valueOf(i);
            initService.dbChange(placeId);
        }


    }



    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final PlaceService placeService;

        public void dbInit(List<Place> places) {
            for (Place place : places) {
                em.persist(place);
            }
        }

        public void dbChange(Long id) {
            placeService.replaceEmptyToNullInPlaceTable(id);
        }



    }

    }
