package com.clova.issuecream.contents.service;

import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.HotTopicNews;
import com.clova.issuecream.contents.repository.HotTopicNewsRepository;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotTopicNewsService {
    private final HotTopicNewsRepository hotTopicNewsRepository;
    private final NewsBoardRepository newsBoardRepository;

    public List<NewsTitleDto> searchHotTopicList() {
        return hotTopicNewsRepository.findTitleDto();
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void saveHotTopicList() {
        List<Long> hotTopicIds = newsBoardRepository.findRandom();
        hotTopicNewsRepository.deleteAll();
        List<HotTopicNews> saveList = new ArrayList<>();
        hotTopicIds.forEach(i->{
            saveList.add(HotTopicNews.builder().newsId(i).build());
        });
        hotTopicNewsRepository.saveAll(saveList);
    }
}
