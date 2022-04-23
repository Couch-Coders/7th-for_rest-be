package couch.forrest.domain.member.controller;

import couch.forrest.domain.member.dto.request.MemberSaveRequestDto;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.filter.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class MemberControllerTest {

    private static final String uid = "2fwe13";
    private static final String email = "GODRI321C@daum.com";
    private static final String name = "가드릭";
    private static final String picture = "https://www.balladang.com";

    @Autowired
    private MemberService memberService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }




    @Test
    void 로그인_테스트() throws Exception {
        memberService.register(email, name, picture, uid);

        ResultActions resultActions = mockMvc.perform(
                        get("/members/login")
                                .header("Authorization", "Bearer " + uid)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("uid").value(uid))
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("picture").value(picture));

    }
}