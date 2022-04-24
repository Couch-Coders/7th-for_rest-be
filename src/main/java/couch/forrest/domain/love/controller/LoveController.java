package couch.forrest.domain.love.controller;

import couch.forrest.domain.love.service.LoveService;
import couch.forrest.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/love")
public class LoveController {
    private final LoveService loveService;

    // 특정 장소에 좋아요를 누른 경우 좋아요를 누른 경우인지 아닌지에 따라 좋아요 해제 및 좋아요 등록
    @GetMapping("/{placeId}")
    public Long likePlace(@PathVariable Long placeId, Authentication authentication){
        Member member = (Member)authentication.getPrincipal();
        return loveService.likePlace(member, placeId);
    }

    @GetMapping("/check/{placeId}")
    public Map<String, String> checkLove(@PathVariable Long placeId, Authentication authentication){
        Member member = (Member)authentication.getPrincipal();
        return loveService.checkPlace(member, placeId);
    }

}
