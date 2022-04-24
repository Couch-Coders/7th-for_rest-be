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

    public PlaceListResponseDto(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.img_src = place.getImage();
        this.tag = place.getTag();
        this.likeCount = place.getLikeCount();
    }

    public static PlaceListResponseDto toDto(Place place) {
        String address = place.getAddress().equals("EMPTY") ? null : place.getAddress();
        String img_src = place.getImage().equals("EMPTY") ? null : place.getImage();
        String tag = place.getTag().equals("EMPTY") ? null : place.getTag();

        return PlaceListResponseDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(address)
                .img_src(img_src)
                .tag(tag)
                .likeCount(place.getLikeCount())
                .build();
    }



}
