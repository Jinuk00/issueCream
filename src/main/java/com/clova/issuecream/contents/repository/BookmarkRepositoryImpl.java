package com.clova.issuecream.contents.repository;

import com.clova.issuecream.contents.entity.QBookmark;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
