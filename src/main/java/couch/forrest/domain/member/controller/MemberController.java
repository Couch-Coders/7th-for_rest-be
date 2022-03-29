//package couch.forrest.domain.member.controller;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import couch.forrest.domain.member.entity.Member;
//import couch.forrest.domain.member.entity.MemberInfo;
//import couch.forrest.domain.member.service.MemberService;
//import couch.forrest.oauth.RequestUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/members")
//public class MemberController {
//    final private FirebaseAuth firebaseAuth;
//    final private MemberService memberService;
//
//    @PostMapping("")
//    public MemberInfo register(@RequestHeader("Authorization") String authorization)
//    {
//        // TOKEN을 가져온다.
//        FirebaseToken decodedToken;
//        try{
//            String token = RequestUtil.getAuthorizationToken(authorization);
//            decodedToken = firebaseAuth.verifyIdToken(token);
//        } catch(IllegalArgumentException | FirebaseAuthException e) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
//                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
//        }
//        // 사용자를 등록한다.
//        Member registeredMember = memberService.register(
//                decodedToken.getName(), decodedToken.getEmail(), decodedToken.getPicture());
//        return new MemberInfo(registeredMember);
//    }
//}
