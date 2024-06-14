package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsDetailDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.enums.CategoryCode;

import java.util.List;

public interface NewsBoardRepositoryCustom {
    List<NewsTitleDto> findByCategory(CategoryCode categoryCode);

    List<NewsTitleDto> findByTitle(String searchTitle);

    NewsDetailDto findDetailById(Long id);
}
