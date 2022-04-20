package couch.forrest.domain.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import couch.forrest.domain.member.dao.MemberRepository;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.dao.ReviewRepository;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.entity.Review;
import lombok.extern.slf4j.Slf4j;
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

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-h2-test.properties"})
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Place place1 = Place.builder()
            .name("롯데월드")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .id(1L)
            .build();

    private static Place place2 = Place.builder()
            .name("한강 박물관")
            .category("박물관")
            .region1("서울")
            .region2("한강구")
            .id(2L)
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
            .name("가드릭")
            .picture("sdfsdf")
            .id(1L)
            .place(place1)
            .member(member1)
            .content("테스트 리뷰 입니다")
            .build();



    private static ReviewSaveRequestDto reviewSaveRequestDto =
            ReviewSaveRequestDto.builder()
                    .content("안녕하세요 반가워요 잘있어요 다시만나요")
                    .placeId(1L)
                    .reviewRating(4.5)
                    .build();


    @DisplayName("댓글 등록 테스트")
    @Test
    void saveReview() throws Exception {

        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findById(place1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);

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

        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findById(place1.getId());
        Optional<Review> review = reviewRepository.findById(review1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);
        if(review.isEmpty())
            reviewRepository.save(review1);

        mockMvc.perform(
                        patch("/reviews/1")
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

        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findById(place1.getId());
        Optional<Review> review = reviewRepository.findById(review1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);
        if(review.isEmpty())
            reviewRepository.save(review1);

        mockMvc.perform(
                        delete("/reviews/1")
                                .header("Authorization", "Bearer " + member1.getUid())
                )
                .andDo(print())
                .andExpect(status().isOk());

    }
}