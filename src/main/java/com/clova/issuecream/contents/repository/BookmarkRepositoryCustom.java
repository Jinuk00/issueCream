package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.Bookmark;

import java.util.List;

public interface BookmarkRepositoryCustom {
    void deleteAllByUserId(String userId);
}
