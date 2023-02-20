package com.laioffer.twich.recommendation;

import com.laioffer.twich.db.entity.UserEntity;
import com.laioffer.twich.model.TypeGroupedItemList;
import com.laioffer.twich.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final UserService userService;

    public RecommendationController(RecommendationService recommendationService, UserService userService) {
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    @GetMapping("/recommendation")
    public TypeGroupedItemList getRecommendation(@AuthenticationPrincipal User user) {
        UserEntity userEntity = user != null ? userService.findByUsername(user.getUsername()) : null;
        return recommendationService.recommendItems(userEntity);
    }
}
