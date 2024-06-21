package com.clova.issuecream.news.service;

import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import com.clova.issuecream.news.dto.NewsTransDto;
import com.clova.issuecream.news.enums.NewsType;
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

//        String timePart = LocalDateTime.now()
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"))
//                .contains("오전") ? "am" : "pm";
        String timePart = "am";
        LocalDate today = LocalDate.now();
        String todayDate = today.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));

        String fileName = fileNames.stream().filter(i -> i.contains(todayDate + "_" + timePart))
                .findAny().orElse("");

        List<NewsBoard> saveList = new ArrayList<>();
        if (!fileName.isEmpty()) {
            ClassPathResource resource = new ClassPathResource("news/" + fileName);
            ObjectMapper objectMapper = new ObjectMapper();

            List<NewsTransDto> newsTransDtos;
            try {
                newsTransDtos = Arrays.asList(objectMapper.readValue(resource.getInputStream(), NewsTransDto[].class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (NewsTransDto dto : newsTransDtos) {
                NewsBoard newsBoard = NewsBoard.builder()
                        .categoryCode(CategoryCode.transByName(dto.getCategory())).build();
                //요약형 
                String content = dto.getContent_smry();

                //키워드 작업
                content = makeKeyWords(newsBoard, content, NewsType.요약형);

                //메인 컨텐츠 분리 작업
                String newsContent = makeNewsContent(content,NewsType.요약형);

                //대화형
                String chatContent = dto.getContent_chat();

                //키워드 작업
                chatContent = makeKeyWords(newsBoard, chatContent, NewsType.대화형);
                
                //메인 컨텐츠 분리 작업
                String newsChatContent = makeNewsContent(chatContent, NewsType.대화형);

                newsBoard.createContent(dto.getTitle(), newsContent, newsChatContent);
                newsBoard.setNewsDate(fileName.substring(0, 13).replaceAll("_", ""));
                newsBoard.checkKeywords();
                if (newsBoard.getKeyWord1() == null && newsBoard.getChatKeyWord1() == null) {
                    continue;
                }
                saveList.add(newsBoard);
            }
            newsBoardRepository.saveAll(saveList);
        }
    }

    private String makeKeyWords(NewsBoard newsBoard, String content, NewsType newsType) {
        String keyWord;
        int chatKeyWordIndex = content.lastIndexOf("키워드");//추후 수정
        if (chatKeyWordIndex != -1) {
            keyWord = content.substring(chatKeyWordIndex + 6);
            content = content.substring(0, chatKeyWordIndex);
            String[] keywords = keyWord.split(",");
            if (newsType.equals(NewsType.요약형)) {
                newsBoard.setKeyWords(keywords);
            } else {
                newsBoard.setChatKeyWords(keywords);
            }
        }
        return content;
    }

    private String deleteContentTitle(String content) {
        int titleStartIndex = content.indexOf("제목");
        int titleEndIndex = content.indexOf("\n", titleStartIndex);
        if (titleStartIndex > -1) {
            String mainContent = content.substring(titleEndIndex);
            return mainContent.substring(mainContent.indexOf("\n") + 1);
        }
        return content;
    }

    private String makeNewsContent(String content, NewsType newsType) {
        String mainContent = deleteContentTitle(content);
        String[] splitContent = mainContent.split("\n");
        StringBuilder newsContent = new StringBuilder();
        for (String s : splitContent) {
            if (s.equals("")) {
                continue;
            }
            newsContent.append(s).append(newsType.equals(NewsType.요약형) ? "\n\n" : "\n");
        }
        newsContent.deleteCharAt(newsContent.lastIndexOf("\n"));
        return newsContent.toString();
    }
}
