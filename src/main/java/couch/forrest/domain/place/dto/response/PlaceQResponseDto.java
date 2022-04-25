package couch.forrest.domain.place.dto.response;


import lombok.*;

@Data
@NoArgsConstructor
public class PlaceQResponseDto {

    private Long id;
    private String name;
    private String address;
    private String img_src;
    private String tag;
    private Long like_count;
    private String phone;
    private String category;
    private String region_1;
    private Double avg;
    private Long review_count;

}
