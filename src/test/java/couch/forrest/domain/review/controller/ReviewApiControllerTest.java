package couch.forrest.domain.review.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.domain.place.dao.PlaceRepository;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.properties"})
@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ReviewApiControllerTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String uid = "213";
    private static final String email = "GODRIC@daum.com";
    private static final String name = "가드릭";
    private static final String picture = "https://www.balladang.com";

    private static Place place = Place.builder()
            .name("롯데월드")
            .category("테마파크")
            .region1("서울")
            .region2("송파구")
            .id(1L)
            .build();

    private static ReviewSaveRequestDto reviewSaveRequestDto =
            ReviewSaveRequestDto.builder()
                    .content("안녕하세요 반가워요 잘있어요 다시만나요")
                    .place(place)
                    .reviewRating(4.5)
                    .build();

    private static ReviewSaveRequestDto reviewUpdateRequestDto =
            ReviewSaveRequestDto.builder()
                    .content("good bye")
                    .place(place)
                    .reviewRating(3.5)
                    .build();

    @BeforeTestClass
    public void BeforeEach() throws Exception {

    }

    @DisplayName("댓글 등록 테스트")
    @Test
    void save() throws Exception {
        memberService.register(email, name, picture, uid);
        placeRepository.save(place);

        mockMvc.perform(
                        post("/reviews")
                                .header("Authorization", "Bearer " + uid)
                                .content(objectMapper.writeValueAsString(reviewSaveRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }
}