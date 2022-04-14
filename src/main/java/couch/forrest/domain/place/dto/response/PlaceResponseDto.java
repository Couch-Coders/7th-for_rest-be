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
public class PlaceResponseDto {
    private Long id;
    private String name;
    private String address;
    private String region1;
    private String region2;
    private String category;
    private String info;
    private String contact;
    private String img_src;
    private String openHours;
    private String cost;
    private String tag;
    private String wayinfo;
    private String link_url;


    private Long likeCount;
    private Long viewCount;

    
    public static PlaceResponseDto toDto(Place place) {
        return PlaceResponseDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .region1(place.getRegion1())
                .region2(place.getRegion2())
                .category(place.getCategory())
                .info(place.getPlaceinfo())
                .cost(place.getCost())
                .img_src(place.getImage())
                .contact(place.getPhone())
                .openHours(place.getOperatingHours())
                .cost(place.getCost())
                .tag(place.getTag())
                .wayinfo(place.getWayinfo())
                .link_url(place.getLink_url())
                .likeCount(place.getLikeCount())
                .viewCount(place.getViewCount())
                .build();
    }



}
