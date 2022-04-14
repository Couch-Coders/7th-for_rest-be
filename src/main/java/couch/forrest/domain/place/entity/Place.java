package couch.forrest.domain.place.entity;


import couch.forrest.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Place extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @Column(name = "placename")
    private String name;

    @Column(name = "average_rating")
    private Double averageRating;

    private Double latitude;
    private Double longitude;

    @Column(name = "like_count")
    private Long likeCount;



    @Column(columnDefinition="TEXT", length = 2048)
    private String placeinfo;

    @Column(name = "img_src",length = 1000)
    private String image;


    @Column(columnDefinition="TEXT", length = 1000)
    private String link_url;

    private String phone;

    @Column(name = "openhours",columnDefinition="TEXT", length = 2048)
    private String operatingHours;

    @Column(columnDefinition="TEXT", length = 2048)
    private String cost;

    @Column(columnDefinition="TEXT", length = 2048)
    private String tag;

    @Column(columnDefinition="TEXT", length = 2048)
    private String wayinfo;

    private String region1;

    @Column(name = "region_2")
    private String region2;

    private String category;

    private String address;

    @Column(name = "view_count")
    private Integer viewCount;

}
