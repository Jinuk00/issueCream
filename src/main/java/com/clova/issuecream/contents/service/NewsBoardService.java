package com.clova.issuecream.contents.service;

import com.clova.issuecream.contents.dto.NewsDetailDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsBoardService {
    private final NewsBoardRepository newsBoardRepository;

    public Page<NewsTitleDto> searchAllNews(int page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        return  newsBoardRepository.findAllByPageable(pageRequest);
    }

    public Page<NewsTitleDto> searchByCategory(String category, int page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        return newsBoardRepository.findByCategory(CategoryCode.transByUrl(category), pageRequest);
    }

    public List<NewsTitleDto> searchByTitle(String searchTitle) {
        return newsBoardRepository.findByTitle(searchTitle);
    }

    public NewsDetailDto searchBtId(Long id) {
        return newsBoardRepository.findDetailById(id);
    }

    public List<NewsTitleDto> searchHotTopics() {
        return newsBoardRepository.findHotTopics();
    }
}
