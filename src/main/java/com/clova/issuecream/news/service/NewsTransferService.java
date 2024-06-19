package com.clova.issuecream.news.service;

import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import com.clova.issuecream.news.dto.NewsTransDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsTransferService {

    private final NewsBoardRepository newsBoardRepository;

    @Transactional
    public void transNews() {
        ClassPathResource resource = new ClassPathResource("news/FINAL_DATA_2024-06-09 20_51_19_duration_1503.2028694152832.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<NewsTransDto> newsTransDtos = new ArrayList<>();
        try {
            newsTransDtos = Arrays.asList(objectMapper.readValue(resource.getInputStream(), NewsTransDto[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<NewsBoard> saveList = new ArrayList<>();
        for (NewsTransDto dto : newsTransDtos) {
            NewsBoard newsBoard = NewsBoard.builder()
                    .categoryCode(CategoryCode.transByName(dto.getCategory())).build();
            String content = dto.getContent();
            log.info("확인 {}", content);
            int keyWordindex = content.lastIndexOf("키워드");//추후 수정
            if (keyWordindex == -1) {
                continue;
            }
            String keyWord = content.substring(keyWordindex+6);
            content = content.substring(0, keyWordindex);
            int titleStartIndex = content.indexOf("제목");
            int titleEndIndex = content.indexOf("\n", titleStartIndex);
            String title = content.substring(titleStartIndex + 5, titleEndIndex);
            String mainContent = content.substring(titleEndIndex);
            mainContent = mainContent.substring(mainContent.indexOf("\n") + 1);
            String[] split = mainContent.split("\n");
            StringBuilder newsContent = new StringBuilder();
            for (String s : split) {
                if (s.equals("")) {
                    continue;
                }
                newsContent.append(s).append("\n");
            }
            StringBuilder keyWords = new StringBuilder();
            newsBoard.setKeyWords(keyWord.split(","));
            if (newsBoard.getKeyWord1().length() >= 10) {
                continue;
            }
            newsContent.insert(0, keyWords + "\n");
            newsBoard.createContent(title, newsContent.toString());
            newsBoard.setNewsDate("20240609");
            saveList.add(newsBoard);
            log.info("객체 {}", newsBoard);
        }
        newsBoardRepository.saveAll(saveList);
    }
}
