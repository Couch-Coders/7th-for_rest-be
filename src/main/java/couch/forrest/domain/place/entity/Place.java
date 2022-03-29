package couch.forrest.domain.place.entity;


import couch.forrest.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private Double averageRating;

    private Integer latitude;
    private Integer longitude;

    private Integer likeCount;

    private String info;

    private String image;

    private String phone;

    private String operatingHours;

    private String region1;

    private String region2;

    private String category;

    private String address;

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
