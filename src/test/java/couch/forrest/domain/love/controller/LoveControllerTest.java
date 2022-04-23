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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
class LoveControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private LoveRepository loveRepository;



    @BeforeEach
    public void beforeEach() {
        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findByName(place1.getName());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }



    private static Place place1 = Place.builder()
            .name("롯데월드043")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .likeCount(0L)
            .build();

    private static Member member1 = Member.builder()
            .uid("qwehwq")
            .email("GODewqeRIC@daum.com")
            .name("가드릭")
            .picture("https://www.balladang.com")
            .build();


    @DisplayName("좋아요 클릭 잘 되는지 테스트")
    @Test
    void likePlace() throws Exception {
        Optional<Place> place = placeRepository.findByName(place1.getName());

        mockMvc.perform(
                        get("/love/"+place.get().getId())
                                .header("Authorization", "Bearer " + member1.getUid())
        )
                .andExpect(status().isOk());


        Optional<Love> love = loveRepository.findByMemberAndPlace(member1, place1);
        Optional<Place> placeAfter = placeRepository.findByName(place1.getName());

        Assertions.assertThat(placeAfter.get().getLikeCount()).isEqualTo(1);
        Assertions.assertThat(love.get().getMember().getId()).isEqualTo(member1.getId());


    }
}