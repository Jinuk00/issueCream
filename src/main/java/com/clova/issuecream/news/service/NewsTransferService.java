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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsTransferService {

    private final NewsBoardRepository newsBoardRepository;

    private static final String NEWS_DIRECTORY = "src/main/resources/news/";


    @Scheduled(cron = "0 0 */3 * * *")
    @Transactional
    public void transNews() {
        log.info("데이터 이관시작!!!");
        List<Path> fileList;
        try {
            fileList = getFileList(NEWS_DIRECTORY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String timePart = LocalDateTime.now()
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"))
//                .contains("오전") ? "am" : "pm";
//        LocalDate today = LocalDate.now();
//        String todayDate = today.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
//        String findFileName = todayDate + "_" + timePart;
        String findFileName = "2024_06_27";
        log.info("찾을 파일 명 {}", findFileName);
        log.info("파일 리스트 {}", fileList);
        Path fileName = fileList.stream().filter(i -> i.getFileName().toString().contains(findFileName))
                .findAny().orElse(null);

        log.info("찾은 파일 {}", fileName != null ? fileName.toString() : "null");

        List<NewsBoard> saveList = new ArrayList<>();
        if (fileName!=null) {
            ObjectMapper objectMapper = new ObjectMapper();

            List<NewsTransDto> newsTransDtos;
            try {
                newsTransDtos = Arrays.asList(objectMapper.readValue(fileName.toFile(), NewsTransDto[].class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (NewsTransDto dto : newsTransDtos) {
                NewsBoard newsBoard = NewsBoard.builder()
                        .categoryCode(CategoryCode.transByName(dto.getCategory())).build();
                log.info("작동 값 {}", dto);
                //요약형
                String content = dto.getContent_smry();

                //키워드 작업
                content = makeKeyWords(newsBoard, content, NewsType.요약형);

                //메인 컨텐츠 분리 작업
                String newsContent = makeNewsContent(content, NewsType.요약형);

                //대화형
                String chatContent = dto.getContent_chat();

                //키워드 작업
                chatContent = makeKeyWords(newsBoard, chatContent, NewsType.대화형);

                //메인 컨텐츠 분리 작업
                String newsChatContent = makeNewsContent(chatContent, NewsType.대화형);

                newsBoard.createContent(dto.getTitle(), newsContent, newsChatContent);
                newsBoard.setNewsDate(fileName.getFileName().toString().substring(0, 13).replaceAll("_", ""));
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
        if (titleStartIndex > -1 && titleEndIndex > -1) {
            String mainContent = content.substring(titleEndIndex);
            return mainContent.substring(mainContent.indexOf("\n") + 1);
        }
        return content;
    }

    private String deleteContentEndTitle(String content) {
        int titleStartIndex = content.indexOf("제목");
        if (titleStartIndex > -1) {
            return content.substring(0, titleStartIndex);
        }
        return content;
    }

    private String makeNewsContent(String content, NewsType newsType) {
        String mainContent = deleteContentTitle(content);
        if (mainContent.startsWith("\n")) {
            mainContent = mainContent.substring(1);
        }
        String[] splitContent = mainContent.split("\n");
        StringBuilder newsContent = new StringBuilder();
        if (mainContent.equals("")) {
            String endTitle = deleteContentEndTitle(content);
            splitContent = endTitle.split("\n");
        }
        for (String s : splitContent) {
            if (s.equals("")) {
                continue;
            }
            newsContent.append(s).append(newsType.equals(NewsType.요약형) ? "\n\n" : "\n");
        }
        int index = newsContent.lastIndexOf("\n");
        if (index > -1) {
            newsContent.deleteCharAt(index);
        }
        return newsContent.toString();
    }

    private List<Path> getFileList(String directory) throws IOException {
        List<Path> fileList = new ArrayList<>();
        Path dirPath = Paths.get(directory);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*_smry_chat.json")) {
            for (Path entry : stream) {
                fileList.add(entry);
            }
        }

        return fileList;
    }
}
