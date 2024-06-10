package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(String userId);
}
