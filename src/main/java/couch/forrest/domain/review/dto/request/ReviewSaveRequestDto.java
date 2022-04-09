package couch.forrest.domain.review.dto.request;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSaveRequestDto {
    private String content;
    private Place place;
    private double reviewRating;
    private String image;

}