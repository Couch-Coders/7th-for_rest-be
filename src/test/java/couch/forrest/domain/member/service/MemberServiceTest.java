package couch.forrest.domain.member.service;

import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application-h2-test.properties"})
@Slf4j
@ActiveProfiles("test")
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

        List<Member> members = memberService.findAll();
        assertThat(members.size()).isEqualTo(1);
        for(int i=0; i<members.size(); i++)
        {
            assertThat(members.get(i).getUid()).isEqualTo(uid);
            assertThat(members.get(i).getEmail()).isEqualTo(email);
            assertThat(members.get(i).getName()).isEqualTo(name);
            assertThat(members.get(i).getPicture()).isEqualTo(picture);
        }
        Member member = memberService.findByUid(uid);

        assertThat(member.getUid()).isEqualTo(uid);

        assertThat(dto.getUid()).isEqualTo(uid);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getPicture()).isEqualTo(picture);

    }

}