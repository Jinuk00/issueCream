package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.NewsBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsBoardRepository extends JpaRepository<NewsBoard, Long>, NewsBoardRepositoryCustom {
    List<NewsBoard> findAllByOrderByNewsDate();

}
