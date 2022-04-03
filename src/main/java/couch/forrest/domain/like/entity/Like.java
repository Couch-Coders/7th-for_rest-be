package couch.forrest.domain.like.entity;

import couch.forrest.domain.base.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Like extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name="like_id")
    private long likeId;
    @Column(name="member_id")
    private long memberId;
    @Column(name="place_id")
    private long placeId;

}
