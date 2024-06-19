package com.clova.issuecream.contents.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.contents.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PutMapping("/bookmark/{newsId}")
    public CommonResponse createBookmark(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, @PathVariable String newsId) {
        bookmarkService.saveBookmark(customOAuth2User.getEmail(), Long.valueOf(newsId));
        return CommonResponse.ok();
    }

    @PostMapping("/scrap/list")
    public CommonResponse searchScrapList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        return CommonResponse.okData(bookmarkService.searchBookmarkList(customOAuth2User.getEmail()));
    }

    @DeleteMapping("/bookmark")
    public CommonResponse deleteBookmark(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, @RequestParam Long newsId) {
        bookmarkService.deleteBookmark(customOAuth2User.getEmail(), newsId);
        return CommonResponse.ok();
    }
}
