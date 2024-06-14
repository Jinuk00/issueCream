package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsDetailDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clova.issuecream.contents.entity.QNewsBoard.*;

@Repository
@RequiredArgsConstructor
public class NewsBoardRepositoryImpl implements NewsBoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<NewsTitleDto> findByCategory(CategoryCode categoryCode) {
        return queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle,
                        newsBoard.newsDate,
                        newsBoard.categoryCode
                ))
                .from(newsBoard)
                .where(newsBoard.categoryCode.eq(categoryCode))
                .orderBy(newsBoard.newsDate.desc(),
                        newsBoard.id.desc())
                .fetch();

    }

    @Override
    public List<NewsTitleDto> findByTitle(String searchTitle) {
        return queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle
                ))
                .from(newsBoard)
                .where(newsBoard.newsTitle.contains(searchTitle))
                .orderBy(newsBoard.newsDate.desc(),
                        newsBoard.id.desc())
                .fetch();
    }

    @Override
    public NewsDetailDto findDetailById(Long id) {
        return queryFactory.select(Projections.bean(
                        NewsDetailDto.class,
                        newsBoard.newsTitle,
                        newsBoard.newsContent,
                        newsBoard.categoryCode
                ))
                .from(newsBoard)
                .where(newsBoard.id.eq(id))
                .fetchOne();
    }
}
