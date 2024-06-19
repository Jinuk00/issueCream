package com.clova.issuecream.contents.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.contents.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NewsBoardController {

    private final NewsBoardService newsBoardService;

    //뉴스 전체 조회
    @PostMapping("/news/search")
    public CommonResponse searchAllNews(@RequestParam int page) {
        return CommonResponse.okData(newsBoardService.searchAllNews(page));
    }

    //카테고리별 뉴스 조회
    @PostMapping("/news/search/{category}")
    public CommonResponse searchNewsByCategory(@PathVariable String category, @RequestParam int page) {
        return CommonResponse.okData(newsBoardService.searchByCategory(category, page));
    }

    @PostMapping("/news/search/title")
    public CommonResponse searchNewsByTitle(@RequestParam String searchTitle) {
        return CommonResponse.okData(newsBoardService.searchByTitle(searchTitle));
    }

    @PostMapping("/news/searchDetail/{id}")
    public CommonResponse searchNewsDetail(@PathVariable Long id) {
        return CommonResponse.okData(newsBoardService.searchBtId(id));
    }

    @PostMapping("/news/hotTopics")
    public CommonResponse searchHotTopics() {
        return CommonResponse.okData(newsBoardService.searchHotTopics());
    }
}
