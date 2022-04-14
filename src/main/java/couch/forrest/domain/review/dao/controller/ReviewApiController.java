package couch.forrest.domain.review.dao.controller;

import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.entity.Review;
import couch.forrest.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto, @AuthenticationPrincipal Member member) {
        return reviewService.save(requestDto, member);
    }

    @PatchMapping("/reviews/{reviewId}")
    public Long update(@PathVariable Long id, @RequestBody ReviewSaveRequestDto requestDto, @AuthenticationPrincipal Member member)
    {
        return reviewService.update(id, requestDto,member);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public Long delete(@PathVariable Long id, @AuthenticationPrincipal Member member) {
        reviewService.delete(id,member);
        return id;
    }

    @GetMapping("/reviews/{placeId}/with-place")
    public List<Review> loadAllReview(@PathVariable Long id) {
        return reviewService.loadAllReview(id);
    }

}
