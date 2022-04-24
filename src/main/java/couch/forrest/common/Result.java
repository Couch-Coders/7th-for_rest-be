package couch.forrest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Result<T> {
    private int count;
    private T data;
}
