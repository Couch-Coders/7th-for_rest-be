package couch.forrest.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.exception.CustomException;
import couch.forrest.oauth.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

    private final UserDetailsService userDetailsService;
    private final FirebaseAuth firebaseAuth;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // get the token from the request
        FirebaseToken decodedToken;
        try{
            String header = RequestUtil.getAuthorizationToken(request.getHeader("Authorization"));
            decodedToken = firebaseAuth.verifyIdToken(header);//디코딩한 firebase 토큰을 반환
        } catch (FirebaseAuthException | IllegalArgumentException | CustomException e) {
            // ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
            return;
        }

        // User를 가져와 SecurityContext에 저장한다.
        try{
            UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());//uid 를 통해 회원 엔티티 조회
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());//인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(authentication);//securityContextHolder 에 인증 객체 저장
        } catch(UsernameNotFoundException e){
            // 기존 유저가 아닐 경우 회원가입 진행 후 로그인
            MemberRegisterResponseDto responseDto = memberService.register(
                    decodedToken.getName(), decodedToken.getEmail(), decodedToken.getPicture(), decodedToken.getUid());

            try{
                UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());//uid 를 통해 회원 엔티티 조회
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());//인증 객체 생성
                SecurityContextHolder.getContext().setAuthentication(authentication);//securityContextHolder 에 인증 객체 저장
            } catch(UsernameNotFoundException error){
                // ErrorMessage 응답 전송
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().write("{\"code\":\"USER_NOT_FOUND\"}");
                return;

            }

        }
        filterChain.doFilter(request, response);
    }
}