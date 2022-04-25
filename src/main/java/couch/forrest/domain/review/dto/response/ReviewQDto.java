package couch.forrest.domain.review.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewQDto {
    private long place_id;
    private int avg;
    private int review_count;
}
