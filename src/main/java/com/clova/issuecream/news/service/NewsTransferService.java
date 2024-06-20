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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsTransferService {

    private final NewsBoardRepository newsBoardRepository;

    @Transactional
    public void transNews() {
        List<String> fileNames;
        ClassPathResource dir = new ClassPathResource("news");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dir.getInputStream()))) {
            fileNames = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("리스트 {}", fileNames);

        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        String todayDate = today.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        List<NewsBoard> saveList = new ArrayList<>();
        fileNames = fileNames.stream().filter(i -> i.contains(todayDate))
                .collect(Collectors.toList());
        for (String fileName : fileNames) {
            ClassPathResource resource = new ClassPathResource("news/" + fileName);
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
                String newsType = fileName.contains("smry") ? "smry" : "chat";
                Optional<NewsBoard> checkNews = saveList.stream().filter(i -> i.getNewsTitle().equals(title)).findAny();
                if (checkNews.isPresent()) {
                    NewsBoard existNews = checkNews.get();
                    existNews.createContent(title, newsContent.toString(), newsType);
                } else {
                    newsContent.insert(0, keyWords + "\n");
                    newsBoard.createContent(title, newsContent.toString(),newsType);
                    newsBoard.setNewsDate(fileName.substring(0, 13).replaceAll("_", ""));
                    saveList.add(newsBoard);
                }
                log.info("객체 {}", newsBoard);
            }
            newsBoardRepository.saveAll(saveList);
        }
    }
}
