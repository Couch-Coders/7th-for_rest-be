package couch.forrest.domain.member.entity;

import couch.forrest.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String name;
    private String picture;


}