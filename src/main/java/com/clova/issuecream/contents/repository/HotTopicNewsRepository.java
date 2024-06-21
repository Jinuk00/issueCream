package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.HotTopicNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotTopicNewsRepository extends JpaRepository<HotTopicNews, Long>, HotTopicNewsRepositoryCustom {

}
