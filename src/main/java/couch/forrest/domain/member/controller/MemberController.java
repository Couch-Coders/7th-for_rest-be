package couch.forrest.domain.member.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.member.entity.MemberInfo;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.oauth.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    final private MemberService memberService;

<<<<<<< Updated upstream
=======
    @PostMapping("")
    public MemberInfo register(@RequestHeader("Authorization") String authorization)
    {
        // TOKEN을 가져온다.
        FirebaseToken decodedToken;
        try{
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch(IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }
        // 사용자를 등록한다.
        Member registeredMember = memberService.register(
                decodedToken.getName(), decodedToken.getEmail(), decodedToken.getPicture(), decodedToken.getUid());
        return new MemberInfo(registeredMember);
    }

    //로그인 파이어베이스 인증 토큰을 Header 에 넣어 로그인을 요청합니다.
    @GetMapping("/me")
    public ResponseEntity<MemberRegisterResponseDto> login(Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        return ResponseEntity.ok(new MemberRegisterResponseDto(member));
    }
>>>>>>> Stashed changes
}
