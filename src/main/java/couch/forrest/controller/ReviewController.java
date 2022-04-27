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
import couch.forrest.exception.CustomException;
import couch.forrest.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final PlaceService placeService;

    @Operation(description = "리뷰 저장")
    @PostMapping("")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto, Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.save(requestDto, member);
    }

    @Parameter(name = "reviewId",required = true)
    @Operation(description = "리뷰 업데이트")
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewSaveRequestDto requestDto, Authentication authentication)
    {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.update(id, requestDto,member);
    }

    @Parameter(name = "reviewId",required = true)
    @Operation(description = "리뷰 삭제")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id , Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        reviewService.delete(id,member);
        return id;
    }

    @Parameter(name = "placeId",required = true)
    @Operation(description = "장소 내의 리뷰 조회")
    @GetMapping("{placeId}/with-place")
    public ResponseEntity<ReviewResult<List<ReviewListResponseDto>>> loadAllReview(@PathVariable Long placeId) {

        Place place = placeService.findOne(placeId)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NOT_FOUND_PLACE, "존재하지 않는 place id :" + placeId);
                });
        List<ReviewListResponseDto> dtos = reviewService.loadAllReview(placeId);
        return ResponseEntity.ok().body(new ReviewResult<>(dtos.size(), dtos));
    }

    @Parameter(name = "reviewId",required = true)
    @Operation(description = "review 조회")
    @GetMapping("/{id}")
    public ReviewResponseDto loadReview(@PathVariable Long id){
        return reviewService.findReviewById(id);
    }

}
