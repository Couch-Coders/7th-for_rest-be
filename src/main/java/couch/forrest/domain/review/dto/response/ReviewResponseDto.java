package couch.forrest.domain.review.dto.response;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long id;
    private Member member;
    private String name;
    private String picture;
    private String reviewRating;
    private String content;
    private String image;
    private LocalDateTime createdDate;

    public ReviewResponseDto(Review review){
        this.id = review.getId();
        this.member = review.getMember();
        this.name = review.getName();
        this.picture = review.getPicture();
        this.reviewRating = String.valueOf(review.getReviewRating());
        this.content = review.getContent();
        this.image = review.getImage();
        this.createdDate = review.getCreatedDate();
    }

}
