package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.BookmarkDto;
import com.clova.issuecream.contents.dto.NewsTitleDto;
import com.clova.issuecream.contents.entity.Bookmark;
import com.clova.issuecream.contents.entity.QBookmark;
import com.clova.issuecream.contents.entity.QNewsBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clova.issuecream.contents.entity.QBookmark.*;
import static com.clova.issuecream.contents.entity.QNewsBoard.*;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom{
    private final JPAQueryFactory queryFactory;


    @Override
    public void deleteAllByUserId(String userId) {
        queryFactory.delete(bookmark)
                .where(bookmark.userId.eq(userId))
                .execute();
    }

    @Override
    public List<NewsTitleDto> findDtoByUserId(String username) {
        return queryFactory.select(Projections.bean(
                        NewsTitleDto.class,
                        newsBoard.id,
                        newsBoard.newsTitle,
                        newsBoard.newsDate,
                        newsBoard.categoryCode
                )).from(bookmark)
                .leftJoin(newsBoard)
                .on(newsBoard.id.eq(bookmark.boardId))
                .where(bookmark.userId.eq(username))
                .fetch();
    }

    @Override
    public Long countByUserId(String username) {
        return queryFactory.select(bookmark.count())
                .from(bookmark)
                .where(bookmark.userId.eq(username))
                .fetchOne();
    }

}
