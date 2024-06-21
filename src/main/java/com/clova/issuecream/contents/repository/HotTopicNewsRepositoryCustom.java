package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsTitleDto;

import java.util.List;

public interface HotTopicNewsRepositoryCustom {
    List<NewsTitleDto> findTitleDto();

}
