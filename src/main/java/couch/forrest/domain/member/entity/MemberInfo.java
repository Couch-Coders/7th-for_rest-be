package couch.forrest.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberInfo {
    private String name;
    private String email;
    private String picture;

    public MemberInfo(Member member) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
