package couch.forrest.domain.member.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import couch.forrest.common.Result;
import couch.forrest.domain.member.dto.request.MemberSaveRequestDto;
import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.member.entity.MemberInfo;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceQResponseDto;
import couch.forrest.oauth.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    final private FirebaseAuth firebaseAuth;
    final private MemberService memberService;

    //로컬 회원 가입 테스트용
    @PostMapping("/local")
    public ResponseEntity<MemberRegisterResponseDto> registerLocalMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto) {
        MemberRegisterResponseDto responseDto = memberService.register(
                memberSaveRequestDto.getEmail(), memberSaveRequestDto.getName(),
                memberSaveRequestDto.getPicture(), memberSaveRequestDto.getUid()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }


    @PostMapping("")
    public ResponseEntity<MemberRegisterResponseDto> register(@RequestHeader("Authorization") String authorization)
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
        MemberRegisterResponseDto responseDto = memberService.register(
                decodedToken.getName(), decodedToken.getEmail(), decodedToken.getPicture(), decodedToken.getUid());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    //로그인 파이어베이스 인증 토큰을 Header 에 넣어 로그인을 요청합니다.
    @GetMapping("/login")
    public ResponseEntity<MemberRegisterResponseDto> login(Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        return ResponseEntity.ok(new MemberRegisterResponseDto(member));
    }

    @GetMapping("/myLike")
    public ResponseEntity<Result<Page<PlaceQResponseDto>>> myLike(Authentication authentication, Pageable pageable) {
        Member member = (Member) authentication.getPrincipal();
        Page<PlaceQResponseDto> dtos = memberService.findMyFavoritePlace(member, pageable);
        return ResponseEntity.ok().body(new Result<>(dtos.getNumberOfElements(), dtos));
    }
    

}
