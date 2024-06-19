package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.NewsDetailDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.enums.CategoryCode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clova.issuecream.contents.entity.QNewsBoard.*;

@Repository
@RequiredArgsConstructor
public class NewsBoardRepositoryImpl implements NewsBoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<NewsTitleDto> findByCategory(CategoryCode categoryCode, Pageable pageable) {
        List<NewsTitleDto> newsTitleDtos = queryFactory.select(Projections.bean(
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> total = queryFactory
                .select(newsBoard.count().coalesce(0L))
                .from(newsBoard)
                .where((newsBoard.categoryCode.eq(categoryCode)));
        return PageableExecutionUtils.getPage(newsTitleDtos, pageable, total::fetchOne);
    }

    @Override
    public List<NewsTitleDto> findByTitle(String searchTitle) {
        if (searchTitle.trim().isEmpty()) {
            return null;
        }
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

    @Override
    public Page<NewsTitleDto> findAllByPageable(Pageable pageable) {
        List<NewsTitleDto> newsTitleDtos = queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle,
                        newsBoard.newsDate,
                        newsBoard.categoryCode
                )).from(newsBoard)
                .orderBy(newsBoard.newsDate.desc(),
                        newsBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> total = queryFactory
                .select(newsBoard.count().coalesce(0L))
                .from(newsBoard);
        return PageableExecutionUtils.getPage(newsTitleDtos, pageable, total::fetchOne);
    }

    @Override
    public List<NewsTitleDto> findHotTopics() {
        return queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle
                )).from(newsBoard)
                .orderBy(newsBoard.newsDate.desc(),
                        newsBoard.id.desc())
                .limit(5L)
                .fetch();
    }
}
