package couch.forrest.domain.review.entity;

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
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;
    private String image;
    private String content;
    @Column(name="review_rating")
    private Double reviewRating;
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY) //레이지 로딩
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;


    @Builder
    public Review(Long id, String image,String name,
                  String content, Double reviewRating,
                  Member member, Place place, String picture) {
        this.picture = picture;
        this.id = id;
        this.image = image;
        this.name = name;
        this.content = content;
        this.reviewRating = reviewRating;
        this.member = member;
        this.place = place;
    }

    public void update(String image, String content, double reviewRating) {
        this.image = image;
        this.content = content;
        this.reviewRating = reviewRating;
    }
}
