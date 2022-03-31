package couch.forrest;


import couch.forrest.domain.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {

            Place place = Place.builder()
                    .name("디아트스페이스 193")
                    .category("전망대")
                    .region1("대전")
                    .region2("유성구")
                    .address("대전 유성구 엑스포로 1")
                    .build();


            em.persist(place);


        }


    }



    }
