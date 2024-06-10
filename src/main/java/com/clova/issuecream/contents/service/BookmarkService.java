package com.clova.issuecream.contents.service;

import com.clova.issuecream.contents.entity.Bookmark;
import com.clova.issuecream.contents.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;


    public void searchBookmarkList(String userId) {
        bookmarkRepository.findByUserId(userId);
    }

    public void saveBookmark(String userId, Long boardId) {
        bookmarkRepository.save(Bookmark.builder()
                .userId(userId).boardId(boardId)
                .build());

    }
}
