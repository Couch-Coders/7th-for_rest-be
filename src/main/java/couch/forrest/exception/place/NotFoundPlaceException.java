package couch.forrest.exception.place;

import java.util.function.Supplier;

public class NotFoundPlaceException extends RuntimeException {

    public NotFoundPlaceException() {
    }

    public NotFoundPlaceException(String message) {
        super(message);
    }

    public NotFoundPlaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPlaceException(Throwable cause) {
        super(cause);
    }

}
