package couch.forrest.domain.place.dto.request;


import couch.forrest.domain.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@AllArgsConstructor
public class PlaceRequestDto {

    private String category;
    private String region_1;
    private String region_2;

}
