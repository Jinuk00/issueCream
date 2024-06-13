package com.clova.issuecream.contents.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.contents.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsBoardController {

    private final NewsBoardService newsBoardService;
    @PostMapping("/news/search")
    public CommonResponse searchAllNews() {
        return CommonResponse.okData(newsBoardService.searchAllNews());
    }

    @PostMapping("/news/search/{category}")
    public CommonResponse searchNewsByCategory(@PathVariable String category) {
        return CommonResponse.okData(newsBoardService.searchByCategory(category));
    }
}
