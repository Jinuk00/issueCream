package com.clova.issuecream.contents.service;

import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsBoardService {
    private final NewsBoardRepository newsBoardRepository;

    public List<NewsTitleDto> searchAllNews() {
        List<NewsBoard> newsBoards = newsBoardRepository.findAllByOrderByNewsDate();
        List<NewsTitleDto> newsTitleDtos = new ArrayList<>();
        newsBoards.forEach(i->{
            newsTitleDtos.add(NewsTitleDto.builder()
                    .id(i.getId()).newsTitle(i.getNewsTitle())
                    .build());
        });
        return newsTitleDtos;
    }

    public List<NewsTitleDto> searchByCategory(String category) {
        return newsBoardRepository.findByCategory(CategoryCode.transByUrl(category));
    }

    public List<NewsTitleDto> searchByTitle(String searchTitle) {
        return newsBoardRepository.findByTitle(searchTitle);
    }
}
