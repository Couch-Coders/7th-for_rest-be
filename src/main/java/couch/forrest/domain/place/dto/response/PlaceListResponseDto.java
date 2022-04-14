package couch.forrest.domain.place.dto.response;


import couch.forrest.domain.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@AllArgsConstructor
public class PlaceListResponseDto {


    private Long id;
    private String name;
    private String address;
    private String img_src;
    private String tag;
    private Long likeCount;

    public static PlaceListResponseDto toDto(Place place) {
        return PlaceListResponseDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .img_src(place.getImage())
                .tag(place.getTag())
                .likeCount(place.getLikeCount())
                .build();
    }

}
