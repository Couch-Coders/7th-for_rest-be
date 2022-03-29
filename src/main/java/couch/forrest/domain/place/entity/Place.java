package couch.forrest.domain.place.entity;


import couch.forrest.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String name;

    @Column(name = "average_rating")
    private Double averageRating;

    private Integer latitude;
    private Integer longitude;

    @Column(name = "like_count")
    private Integer likeCount;

    private String info;

    private String image;

    private String phone;

    @Column(name = "operating_hours")
    private String operatingHours;

    private String region1;

    private String region2;

    private String category;

    private String address;

    @Column(name = "view_count")
    private Integer viewCount;

    @Builder
    public Place(Long id, String name, Double averageRating, Integer latitude, Integer longitude, Integer likeCount, String info, String image, String phone, String operatingHours, String region1, String region2, String category, String address, Integer viewCount) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.likeCount = likeCount;
        this.info = info;
        this.image = image;
        this.phone = phone;
        this.operatingHours = operatingHours;
        this.region1 = region1;
        this.region2 = region2;
        this.category = category;
        this.address = address;
        this.viewCount = viewCount;
    }
}
