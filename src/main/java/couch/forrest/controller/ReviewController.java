package couch.forrest.controller;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.place.service.PlaceService;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.dto.response.ReviewListResponseDto;
import couch.forrest.domain.review.dto.response.ReviewResponseDto;
import couch.forrest.domain.review.dto.response.ReviewResult;
import couch.forrest.domain.review.entity.Review;
import couch.forrest.domain.review.service.ReviewService;
import couch.forrest.exception.place.NotFoundPlaceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final PlaceService placeService;

    @PostMapping("")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto, Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.save(requestDto, member);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewSaveRequestDto requestDto, Authentication authentication)
    {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.update(id, requestDto,member);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id , Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        reviewService.delete(id,member);
        return id;
    }

    @GetMapping("{placeId}/with-place")
    public ResponseEntity<ReviewResult<List<ReviewListResponseDto>>> loadAllReview(@PathVariable Long placeId) {

        Place place = placeService.findOne(placeId).orElseThrow(() -> new NotFoundPlaceException("존재하지 않는 place_id 입니다. place_id : " + placeId));
        List<ReviewListResponseDto> dtos = reviewService.loadAllReview(placeId);
        return ResponseEntity.ok().body(new ReviewResult<>(dtos.size(), dtos));
    }

    @GetMapping("/{id}")
    public ReviewResponseDto loadReview(@PathVariable Long id){
        return reviewService.findReviewById(id);
    }

}
