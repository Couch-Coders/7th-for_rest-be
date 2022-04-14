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

<<<<<<< HEAD
<<<<<<< HEAD
    private String link_url;
=======
    private String tag;
    private String cost;
    private String wayinfo;
>>>>>>> fixPlace
=======

    @Column(columnDefinition="TEXT", length = 1000)
    private String link_url;
>>>>>>> main

    private String phone;

    @Column(name = "openhours",columnDefinition="TEXT", length = 2048)
    private String operatingHours;

<<<<<<< HEAD
<<<<<<< HEAD
=======
    @Column(columnDefinition="TEXT", length = 2048)
>>>>>>> main
    private String cost;

    @Column(columnDefinition="TEXT", length = 2048)
    private String tag;

    @Column(columnDefinition="TEXT", length = 2048)
    private String wayinfo;

<<<<<<< HEAD
=======
>>>>>>> fixPlace
    @Column(name = "region_1")
=======
>>>>>>> main
    private String region1;

    private String region2;

    private String category;

    private String address;

    @Column(name = "view_count")
    private Long viewCount;

<<<<<<< HEAD

    @Builder
<<<<<<< HEAD
    public Place(Long id, String name, String tag, String cost, String wayinfo,Double averageRating, Integer latitude, Integer longitude, long likeCount, String info, String image, String phone, String operatingHours, String region1, String region2, String category, String address, long viewCount) {
=======
    public Place(Long id, String name, String tag, String cost, String wayinfo,Double averageRating, Integer latitude, Integer longitude, Integer likeCount, String info, String image, String phone, String operatingHours, String region1, String region2, String category, String address, Integer viewCount) {
>>>>>>> fixPlace
        this.id = id;
        this.tag = tag;
        this.cost = cost;
        this.wayinfo = wayinfo;
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
=======
>>>>>>> main
}
