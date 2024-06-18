package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {

    List<Bookmark> findByUserId(String userId);

    Optional<Bookmark> findByBoardIdAndUserId(Long newsId, String userId);
}
