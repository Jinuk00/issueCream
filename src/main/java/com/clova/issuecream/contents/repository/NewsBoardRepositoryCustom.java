package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsDetailDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsBoardRepositoryCustom {
    Page<NewsTitleDto> findByCategory(CategoryCode categoryCode,Pageable pageable);

    List<NewsTitleDto> findByTitle(String searchTitle);

    NewsDetailDto findDetailById(Long id);

    Page<NewsTitleDto> findAllByPageable(Pageable pageable);

    List<NewsTitleDto> findHotTopics();
}
