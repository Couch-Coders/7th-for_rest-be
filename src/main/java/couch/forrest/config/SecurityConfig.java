package couch.forrest.config;

import com.google.api.Http;
import com.google.firebase.auth.FirebaseAuth;
import couch.forrest.config.auth.AuthFilterContainer;
import couch.forrest.domain.member.service.MemberService;
import couch.forrest.filter.JwtFilter;
import couch.forrest.filter.MockJwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthFilterContainer authFilterContainer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제
                .csrf().disable() // csrf 보안 토큰 disable 처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // 요청에 대한 권한 지정
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight Request 허용해주기
                .anyRequest().authenticated() // 모든 요청이 인증되어야한다.
                .and()
                .addFilterBefore(authFilterContainer.getFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 회원가입, 메인페이지, 리소스
        web.ignoring().antMatchers(HttpMethod.POST, "/users")
                .antMatchers("/")
                .antMatchers("/resources/**")
                .antMatchers("/places/**")
                .antMatchers("/resources/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                .antMatchers("/vendor/**")
                .antMatchers("/css/**")
                .antMatchers(HttpMethod.GET, "/reviews/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/manifest.json")
                .antMatchers("/logo192.png")
                .antMatchers("/asset-manifest.json")
                .antMatchers("/logo512.png")
                .antMatchers("/index.html")
                .antMatchers("/static/**");
    }
}