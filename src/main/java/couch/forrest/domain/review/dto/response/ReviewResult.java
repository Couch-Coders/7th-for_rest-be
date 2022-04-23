package couch.forrest.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewResult<T> {
    private int count;
    private T reviews;
}
