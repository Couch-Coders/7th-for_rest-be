package couch.forrest.domain.love.entity;

import couch.forrest.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Love extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="love_id")
    private long loveId;
    @Column(name="member_id")
    private long memberId;
    @Column(name="place_id")
    private long placeId;

    @Builder
    public Love(long loveId, long memberId, long placeId) {
        this.loveId = loveId;
        this.memberId = memberId;
        this.placeId = placeId;
    }

}