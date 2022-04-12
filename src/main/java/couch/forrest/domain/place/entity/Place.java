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

    private Integer latitude;
    private Integer longitude;

    @Column(name = "like_count")
    private Long likeCount;

    private String info;

    @Column(name = "img_src")
    private String image;

    private String link_url;

    private String phone;

    @Column(name = "openhours")
    private String operatingHours;

    private String cost;

    private String tag;

    private String wayinfo;

    private String region1;

    private String region2;

    private String category;

    private String address;

    @Column(name = "view_count")
    private Long viewCount;

}
