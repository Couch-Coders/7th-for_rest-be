package couch.forrest.domain.member.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveRequestDto {

    private String uid;
    private String email;
    private String name;
    private String picture;
}