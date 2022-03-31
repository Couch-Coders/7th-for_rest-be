package couch.forrest.domain.member.dto.response;

import couch.forrest.domain.member.entity.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
public class MemberRegisterResponseDto {

    private Long memberId;
    private String uid;
    private String email;
    private String name;
    private String picture;
    private LocalDateTime createdDate;

    public MemberRegisterResponseDto(Member member) {
        this.memberId = member.getId();
        this.uid = member.getUid();
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
    }
}