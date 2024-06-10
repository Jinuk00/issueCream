package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.NewsBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsBoardRepository extends JpaRepository<NewsBoard,Long> {

}
