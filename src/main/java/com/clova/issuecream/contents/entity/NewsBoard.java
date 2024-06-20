package com.clova.issuecream.contents.entity;

import com.clova.issuecream.contents.enums.CategoryCode;
import com.clova.issuecream.contents.enums.CategoryCodeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "news_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamicUpdate
@ToString
public class NewsBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("id값")
    Long id;

    @Column(name = "category_cd")
    @Comment("카테고리 코드")
    @Convert(converter = CategoryCodeConverter.class)
    CategoryCode categoryCode;

    @Column(name = "news_title")
    @Comment("뉴스 제목")
    String newsTitle;

    @Column(name = "news_content")
    @Comment("뉴스 본문")
    String newsContent;

    @Column(name = "news_chat_content")
    @Comment("뉴스 대화형 본문")
    String newsChatContent;

    @Column(name = "news_dt")
    @Comment("뉴스 기사 날짜")
    String newsDate;

    @Column(name = "keyword1")
    @Comment("키워드1")
    String keyWord1;

    @Column(name = "keyword2")
    @Comment("키워드2")
    String keyWord2;

    @Column(name = "keyword3")
    @Comment("키워드3")
    String keyWord3;

    @Column(name = "type")
    @Comment("뉴스 타입")
    String type;

    public void createContent(String title, String newsContent, String newsType) {
        this.newsTitle = title;
        if (newsType.equals("smry")) {
            this.newsContent = newsContent;
        } else {
            this.newsChatContent = newsContent;
        }
    }

    public void setKeyWords(String[] keyWords) {
        if (keyWords.length >= 1) {
            keyWord1 = keyWords[0];
        }
        if (keyWords.length >= 2) {
            keyWord2 = keyWords[1];
        }
        if (keyWords.length >= 3) {
            keyWord3 = keyWords[2];
        }
    }

    public void setNewsDate(String date) {
        newsDate = date;
    }
}
