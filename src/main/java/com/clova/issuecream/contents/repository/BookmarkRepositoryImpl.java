package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.dto.BookmarkDto;
import com.clova.issuecream.contents.entity.Bookmark;
import com.clova.issuecream.contents.entity.QBookmark;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clova.issuecream.contents.entity.QBookmark.*;

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
    public List<BookmarkDto> findDtoByUserId(String username) {
        return queryFactory.select(Projections.bean(
                        BookmarkDto.class,
                        bookmark.boardId,
                        bookmark.newsTitle
                )).from(bookmark)
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
