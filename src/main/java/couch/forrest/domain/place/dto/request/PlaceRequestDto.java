package couch.forrest.domain.place.dto.request;


import couch.forrest.domain.place.entity.Place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@AllArgsConstructor
public class PlaceRequestDto {

    @Schema(example = "공원", required = true)
    private String category;
    @Schema(example = "경기", required = true)
    private String region_1;
    @Schema(example = "성남시", required = false)
    private String region_2;

}
