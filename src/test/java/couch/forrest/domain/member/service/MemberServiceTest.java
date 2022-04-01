package couch.forrest.domain.member.service;

import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {"spring.config.location=classpath:application-test.properties"})
@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberServiceTest {

    private static final String uid = "abcd";
    private static final String email = "godric@naver.com";
    private static final String name = "가드릭";
    private static final String picture = "https://www.balladang.com";

    @Autowired
    private MemberService memberService;

    @Test
    void 유저_저장_테스트(){
        MemberRegisterResponseDto dto = memberService.register(email, name, picture, uid);
        assertEquals(dto.getUid(),uid);
        assertEquals(dto.getEmail(),email);
        assertEquals(dto.getName(),name);
        assertEquals(dto.getPicture(),picture);

    }

}