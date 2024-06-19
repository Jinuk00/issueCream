package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.BookmarkDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.Bookmark;

import java.util.List;

public interface BookmarkRepositoryCustom {
    void deleteAllByUserId(String userId);

    List<NewsTitleDto> findDtoByUserId(String username);

    Long countByUserId(String username);
}
