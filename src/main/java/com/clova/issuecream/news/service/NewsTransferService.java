package com.clova.issuecream.news.service;

import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import com.clova.issuecream.news.dto.NewsTransDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsTransferService {

    private final NewsBoardRepository newsBoardRepository;


    public void transNews() {
        ClassPathResource resource = new ClassPathResource("news/2024-06-09 pm.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<NewsTransDto> newsTransDtos = new ArrayList<>();
        try {
            newsTransDtos = Arrays.asList(objectMapper.readValue(resource.getInputStream(), NewsTransDto[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (NewsTransDto dto : newsTransDtos) {
            NewsBoard newsBoard = NewsBoard.builder()
                    .categoryCode(CategoryCode.transByName(dto.getCategory())).build();
            String content = dto.getContent();
            log.info("본문 {}", content);
            int keyWordindex = content.lastIndexOf("키워드");//추후 수정
            String keyWord = content.substring(keyWordindex+6);
            int titleIndex = content.indexOf("제목");
            int index = content.indexOf("\n");

            log.info("본문찾은거 {}", content.substring(titleIndex));
            log.info("첫번째 엔터 {}", content.substring(index));
            log.info("제목만 {}", content.substring(titleIndex+5, index));

            log.info("{}", keyWord);
            break;
        }
    }
}
