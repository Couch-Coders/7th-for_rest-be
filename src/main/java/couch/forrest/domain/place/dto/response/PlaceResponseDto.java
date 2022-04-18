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
        String address = place.getAddress().equals("EMPTY") ? null : place.getAddress();
        String info = place.getPlaceinfo().equals("EMPTY") ? null : place.getPlaceinfo();
        String contact = place.getPhone().equals("EMPTY") ? null : place.getPhone();
        String img_src = place.getImage().equals("EMPTY") ? null : place.getImage();
        String openHours = place.getOperatingHours().equals("EMPTY") ? null : place.getOperatingHours();
        String cost = place.getCost().equals("EMPTY") ? null : place.getCost();
        String tag = place.getTag().equals("EMPTY") ? null : place.getTag();
        String wayinfo = place.getWayinfo().equals("EMPTY") ? null : place.getWayinfo();
        String link_url = place.getLink_url().equals("EMPTY") ? null : place.getLink_url();

        return PlaceResponseDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(address)
                .region1(place.getRegion1())
                .region2(place.getRegion2())
                .category(place.getCategory())
                .info(info)
                .cost(cost)
                .img_src(img_src)
                .contact(contact)
                .openHours(openHours)
                .cost(cost)
                .tag(tag)
                .wayinfo(wayinfo)
                .link_url(link_url)
                .likeCount(place.getLikeCount())
                .viewCount(place.getViewCount())
                .build();
    }



}
