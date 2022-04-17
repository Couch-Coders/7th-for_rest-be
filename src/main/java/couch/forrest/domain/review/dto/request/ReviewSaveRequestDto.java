package couch.forrest.domain.review.dto.request;

import couch.forrest.domain.place.entity.Place;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReviewSaveRequestDto {
    private String content;
    private Long placeId;
    private double reviewRating;
    private String image;

}