package couch.forrest.domain.love.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import couch.forrest.domain.love.dao.LoveRepository;
import couch.forrest.domain.love.entity.Love;
import couch.forrest.domain.member.dao.MemberRepository;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.dao.ReviewRepository;
import couch.forrest.domain.review.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-h2-test.properties"})
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class LoveControllerTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private LoveRepository loveRepository;
    @Autowired
    private MockMvc mockMvc;

    private static Place place1 = Place.builder()
            .name("롯데월드")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .likeCount(0L)
            .id(1L)
            .build();

    private static Member member1 = Member.builder()
            .uid("qwehwq")
            .email("GODRIC@daum.com")
            .name("가드릭")
            .picture("https://www.balladang.com")
            .build();


    @BeforeEach
    void setUp() {
        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findById(place1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);
    }

    @DisplayName("좋아요 클릭 잘 되는지 테스트")
    @Test
    void likePlace() throws Exception {
        mockMvc.perform(
                        get("/love/"+place1.getId())
                                .header("Authorization", "Bearer " + member1.getUid())
        )
                .andDo(print())
                .andExpect(status().isOk());


        Optional<Place> place = placeRepository.findById(place1.getId());
        Optional<Love> love = loveRepository.findById(1L);

        Assertions.assertThat(place.get().getLikeCount()).isEqualTo(1);
        Assertions.assertThat(love.get().getMember().getId()).isEqualTo(member1.getId());


    }
}