package couch.forrest.domain.review.service;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-h2-test.properties"})
@Slf4j
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    private static Place place1 = Place.builder()
            .name("롯데월드")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .id(1L)
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

    private static Review review1 = Review.builder()
            .name("가드릭")
            .picture("sdfsdf")
            .id(1L)
            .place(place1)
            .member(member1)
            .content("테스트111111111 리뷰 입니다")
            .build();

    private static ReviewSaveRequestDto reviewSaveRequestDto =
            ReviewSaveRequestDto.builder()
                    .content("안녕하세요 반가워요 잘있어요 다시만나요")
                    .placeId(1L)
                    .reviewRating(4.5)
                    .build();

    @BeforeEach
    void setUp(){
        Optional<Member> member = memberRepository.findByUid(member1.getUid());
        Optional<Place> place = placeRepository.findById(place1.getId());
        Optional<Review> review = reviewRepository.findById(review1.getId());

        if(member.isEmpty())
            memberRepository.save(member1);
        if(place.isEmpty())
            placeRepository.save(place1);
        if(review.isEmpty())
            reviewRepository.save(review1);
    }


    @DisplayName("권한없는 사용자 댓글 수정 테스트")
    @Test
    void update() {
        try {
            reviewService.update(review1.getId(), reviewSaveRequestDto, member2);
        }
        catch(IllegalArgumentException e)
        {
            assertThat(e.getMessage()).isEqualTo("해당 요청에 권한이 없습니다.");
        }
    }

    @DisplayName("권한없는 사용자 댓글 삭제 테스트")
    @Test
    void delete() {
        try {
            reviewService.delete(review1.getId(), member2);
        }
        catch(IllegalArgumentException e)
        {
            assertThat(e.getMessage()).isEqualTo("해당 요청에 권한이 없습니다.");
        }
    }

}