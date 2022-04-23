package couch.forrest.domain.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.InternalException;
import couch.forrest.domain.member.dao.MemberRepository;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.dao.ReviewRepository;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class ReviewControllerTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    private static Place place1 = Place.builder()
            .name("갱냄롯데월드")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .build();

    private static Member member1 = Member.builder()
            .uid("qwehwq")
            .email("GODRIC@daum.com")
            .name("가드릭")
            .picture("https://www.balladang.com")
            .build();

    private static Member member2 = Member.builder()
            .uid("gdfui")
            .email("PUMKIN@daum.com")
            .name("펌킨")
            .picture("https://www.PUMKIN.com")
            .build();

    private static  Review review1 = Review.builder()
            .name("가드릭123")
            .picture("sdfsdf")
            .place(place1)
            .member(member1)
            .content("테스트111111111 리뷰 입니다")
            .build();


    @BeforeEach
    void setUp() {
        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findByName(place1.getName());
        Optional<Review> review = reviewRepository.findByName(review1.getName());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);


        if(review.isEmpty()) {
            Optional<Member> member2 = memberRepository.findByUid(member1.getUid());
            Optional<Place> place2 = placeRepository.findByName(place1.getName());
            Review review2 = Review.builder()
                    .name("가드릭123")
                    .picture("sdfsdf")
                    .place(place2.get())
                    .member(member2.get())
                    .content("테스트111111111 리뷰 입니다")
                    .build();
            reviewRepository.save(review2);
        }
    }


    @DisplayName("댓글 등록 테스트")
    @Test
    void saveReview() throws Exception {
        Optional<Place> place = placeRepository.findByName(place1.getName());

        ReviewSaveRequestDto reviewSaveRequestDto =
                ReviewSaveRequestDto.builder()
                        .content("안녕하세요 반가워요 잘있어요 다시만나요")
                        .placeId(place.get().getId())
                        .reviewRating(4.5)
                        .build();


        mockMvc.perform(
                        post("/reviews")
                                .header("Authorization", "Bearer " + member1.getUid())
                                .content(objectMapper.writeValueAsString(reviewSaveRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }



    @DisplayName("댓글 수정 테스트")
    @Test
    void updateReview() throws Exception {
        Optional<Place> place = placeRepository.findByName(place1.getName());
        Optional<Review> review = reviewRepository.findByName(review1.getName());

        ReviewSaveRequestDto reviewSaveRequestDto =
                ReviewSaveRequestDto.builder()
                        .content("안녕하세요 반가워요 11111")
                        .placeId(place.get().getId())
                        .reviewRating(4.5)
                        .build();

        mockMvc.perform(
                        patch("/reviews/"+review.get().getId())
                                .header("Authorization", "Bearer " + member1.getUid())
                                .content(objectMapper.writeValueAsString(reviewSaveRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteReview() throws Exception {

        Optional<Review> review = reviewRepository.findByName(review1.getName());

        mockMvc.perform(
                        delete("/reviews/"+  review.get().getId())
                                .header("Authorization", "Bearer " + member1.getUid())
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @DisplayName("댓글 조회 테스트")
    @Test
    void searchReview() throws Exception {
        Optional<Place> place = placeRepository.findByName(place1.getName());

        mockMvc.perform(
                        get("/reviews/"+place.get().getId()+"/with-place")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}