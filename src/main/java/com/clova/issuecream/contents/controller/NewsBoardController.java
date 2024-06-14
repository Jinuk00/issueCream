package com.clova.issuecream.contents.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.contents.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/news/search/title")
    public CommonResponse searchNewsByTitle(@RequestParam String searchTitle) {
        return CommonResponse.okData(newsBoardService.searchByTitle(searchTitle));
    }

    @PostMapping("/news/searchDetail/{id}")
    public CommonResponse searchNewsDetail(@PathVariable Long id) {
        return CommonResponse.okData(newsBoardService.searchBtId(id));
    }
}
