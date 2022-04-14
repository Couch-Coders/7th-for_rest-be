package couch.forrest.domain.review.service;

import couch.forrest.domain.member.dto.response.MemberRegisterResponseDto;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;
import couch.forrest.domain.review.dao.ReviewRepository;
import couch.forrest.domain.review.dto.request.ReviewSaveRequestDto;
import couch.forrest.domain.review.entity.Review;
import couch.forrest.exception.CustomException;
import couch.forrest.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;


    public Long save(ReviewSaveRequestDto requestDto, Member member)
    {

        Review review = Review.builder()
                .reviewRating(requestDto.getReviewRating())
                .content(requestDto.getContent())
                .picture(member.getPicture())
                .member(member)
                .name(member.getName())
                .image(requestDto.getImage())
                .place(requestDto.getPlace())
                .build();

        return reviewRepository.save(review).getId();
    }

    public Long update(Long id, ReviewSaveRequestDto requestDto, Member member)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존제하지 않습니다. id="+id));

        if (review.getMember().getId() != member.getId()){
            throw new IllegalArgumentException(ErrorCode.FORBIDDEN_USER.getDetail());
        }
        review.update(requestDto.getImage()
        ,requestDto.getContent()
        ,requestDto.getReviewRating());

        return id;
    }

    public void delete(Long id, Member member)
    {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존제하지 않습니다. id="+id));
        if (review.getMember().getId() != member.getId()){
            throw new IllegalArgumentException(ErrorCode.FORBIDDEN_USER.getDetail());
        }
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public List<Review> loadAllReview(Long placeId) {
        return reviewRepository.loadReview(placeId);
    }
}
