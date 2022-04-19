package couch.forrest.domain.review.controller;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.dto.response.ReviewListResponseDto;
import couch.forrest.domain.review.entity.Review;
import couch.forrest.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto, Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.save(requestDto, member);
    }

    @PatchMapping("{reviewId}")
    public Long update(@PathVariable Long id, @RequestBody ReviewSaveRequestDto requestDto, Authentication authentication)
    {
        Member member = (Member)authentication.getPrincipal();
        return reviewService.update(id, requestDto,member);
    }

    @DeleteMapping("{reviewId}")
    public Long delete(@PathVariable Long id , Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();
        reviewService.delete(id,member);
        return id;
    }

    @GetMapping("{placeId}/with-place")
    public List<ReviewListResponseDto> loadAllReview(@PathVariable Long id) {
        return reviewService.loadAllReview(id);
    }

}
