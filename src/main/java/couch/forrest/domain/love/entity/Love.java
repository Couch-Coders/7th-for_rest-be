package couch.forrest.domain.love.entity;

import couch.forrest.domain.base.BaseTimeEntity;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;
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
    private long id; //Spring data Jpa Repository [findById] 사용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public Love(long id, Member member, Place place) {
        this.id = id;
        this.member = member;
        this.place = place;
    }
}