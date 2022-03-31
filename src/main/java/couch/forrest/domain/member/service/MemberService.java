package couch.forrest.domain.member.service;

import couch.forrest.domain.member.dao.MemberRepository;
import couch.forrest.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    final private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findById(username).get();
    }

    @Transactional
    public Member register(String email, String name, String picture) {
        Member customUser = Member.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .build();
        memberRepository.save(customUser);
        return customUser;
    }
}
