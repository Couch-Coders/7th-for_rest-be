package couch.forrest.domain.member.service;

import couch.forrest.domain.member.dao.MemberRepository;
import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.dto.response.PlaceListResponseDto;
import couch.forrest.domain.place.dto.response.PlaceQResponseDto;
import couch.forrest.exception.CustomException;
import couch.forrest.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService implements UserDetailsService{

    final private MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String uid) throws UsernameNotFoundException {
        return memberRepository.findByUid(uid)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 회원이 존재하지 않습니다.");
                });
    }

    @Transactional(readOnly = true)
    public Member findByUid(String uid) throws UsernameNotFoundException {
        return memberRepository.findByUid(uid)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 회원이 존재하지 않습니다.");
                });
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) throws UsernameNotFoundException {
        return (Member) memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 회원이 존재하지 않습니다.");
                });
    }

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional
    public MemberRegisterResponseDto register(String email, String name, String picture, String uid) {
        Optional<Member> optionalMember = memberRepository.findByUid(uid);
        if (optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_USER, "해당 계정으로 이미 회원가입을 했습니다.");
        }
        Member member = Member.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .picture(picture)
                .build();

        return new MemberRegisterResponseDto(memberRepository.save(member));
    }


    public Page<PlaceQResponseDto> findMyFavoritePlace(Member member, Pageable pageable){
        long memberId = member.getId();
        return memberRepository.findMyFavoritePlaces(memberId, pageable);
    }

    public List<PlaceQResponseDto> myPageLovedPlace(Member member){
        long memberId = member.getId();
        return memberRepository.myPageLovedPlaces(memberId);
    }

}
