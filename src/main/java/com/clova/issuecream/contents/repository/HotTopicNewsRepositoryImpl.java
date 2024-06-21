package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.QHotTopicNews;
import com.clova.issuecream.contents.entity.QNewsBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.clova.issuecream.contents.entity.QHotTopicNews.*;
import static com.clova.issuecream.contents.entity.QNewsBoard.*;

@Repository
@RequiredArgsConstructor
public class HotTopicNewsRepositoryImpl implements HotTopicNewsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<NewsTitleDto> findTitleDto() {
        return queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle,
                        newsBoard.newsDate,
                        newsBoard.categoryCode
                )).from(hotTopicNews)
                .leftJoin(newsBoard)
                .on(newsBoard.id.eq(hotTopicNews.newsId))
                .fetch();
    }
}
