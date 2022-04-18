package couch.forrest.domain.review.dto.response;


import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ReviewListResponseDto {
    private Long id;
    private Long memberId;
    private String name;
    private String picture;
    private double reviewRating;
    private String content;
    private String image;
    private LocalDateTime modifiedDate;

    public ReviewListResponseDto(Review review){
        this.id = review.getId();
        this.memberId = review.getMember().getId();
        this.name = review.getName();
        this.picture = review.getPicture();
        this.reviewRating = review.getReviewRating();
        this.content = review.getContent();
        this.image = review.getImage();
        this.modifiedDate = review.getLastModifiedDate();
    }

}
