package com.clova.issuecream.contents.service;

import com.clova.issuecream.common.exception.UserException;
import com.clova.issuecream.contents.entity.Bookmark;
import com.clova.issuecream.contents.entity.NewsBoard;
import com.clova.issuecream.contents.repository.BookmarkRepository;
import com.clova.issuecream.contents.repository.NewsBoardRepository;
import com.clova.issuecream.login.entity.Member;
import com.clova.issuecream.login.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final NewsBoardRepository newsBoardRepository;

    public void searchBookmarkList(String userId) {
        bookmarkRepository.findByUserId(userId);
    }

    public void saveBookmark(String email, Long newsId) {
        Optional<Member> checkMember = memberRepository.findByEmail(email);
        if (checkMember.isEmpty()) {
            return;
        }
        Optional<NewsBoard> checkBoard = newsBoardRepository.findById(newsId);
        if (checkBoard.isEmpty()) {
            throw new UserException("존재하지 않는 뉴스기사 입니다.");
        }

        Member member = checkMember.get();
        String userId = member.getUsername();
        Optional<Bookmark> checkBookmark = bookmarkRepository.findByBoardIdAndUserId(newsId, userId);
        if (checkBookmark.isPresent()) {
            return;
        }
        bookmarkRepository.save(Bookmark.builder()
                .userId(userId).boardId(newsId).newsTitle(checkBoard.get().getNewsTitle())
                .build());

    }

    @Transactional
    public void deleteBookmark(String email, Long newsId) {
        Optional<Member> checkMember = memberRepository.findByEmail(email);
        if (checkMember.isEmpty()) {
            return;
        }
        Optional<Bookmark> checkBookmark = bookmarkRepository.findById(newsId);
        if (checkBookmark.isEmpty()) {
            bookmarkRepository.delete(checkBookmark.get());
        }
    }
    
    //탈퇴시 사용
    public void deleteAllBookmark(String userId) {
        bookmarkRepository.deleteAllByUserId(userId);
    }
}
