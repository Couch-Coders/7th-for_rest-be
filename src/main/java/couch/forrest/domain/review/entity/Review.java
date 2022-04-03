package couch.forrest.domain.review.entity;

import couch.forrest.domain.base.BaseTimeEntity;
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
    private Long reviewId;
    private String image;
    private String content;
    @Column(name="review_rating")
    private Double reviewRating;
    @Column(name="member_id",nullable = false, unique = true)
    private Long memberId;
    @Column(name="place_id",nullable = false, unique = true)
    private Long placeId;


    @Builder
    public Review(Long reviewId, String image, String content, Double reviewRating, Long memberId, Long placeId) {
        this.reviewId = reviewId;
        this.image = image;
        this.content = content;
        this.reviewRating = reviewRating;
        this.memberId = memberId;
        this.placeId = placeId;
    }

}
