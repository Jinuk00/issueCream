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

    @Column(name = "chat_keyword1")
    @Comment("대화형 키워드1")
    String chatKeyWord1;

    @Column(name = "chat_keyword2")
    @Comment("대화형 키워드2")
    String chatKeyWord2;

    @Column(name = "chat_keyword3")
    @Comment("대화형 키워드3")
    String chatKeyWord3;

    public void createContent(String title, String newsContent,String newsChatContent) {
        this.newsTitle = title;
        this.newsContent = newsContent;
        this.newsChatContent = newsChatContent;
    }

    public void setKeyWords(String[] keyWords) {
        if (keyWords.length >= 1 && keyWords[0].length()<=10) {
            keyWord1 = keyWords[0].replaceAll("#","");
        }
        if (keyWords.length >= 2 && keyWords[1].length()<=10) {
            keyWord2 = keyWords[1].replaceAll("#","");
        }
        if (keyWords.length >= 3 && keyWords[2].length()<=10) {
            keyWord3 = keyWords[2].replaceAll("#","");
        }
    }

    public void setChatKeyWords(String[] keyWords) {
        if (keyWords.length >= 1 && keyWords[0].length()<=10) {
            chatKeyWord1 = keyWords[0];
        }
        if (keyWords.length >= 2 && keyWords[1].length()<=10) {
            chatKeyWord2 = keyWords[1];
        }
        if (keyWords.length >= 3 && keyWords[2].length()<=10) {
            chatKeyWord3 = keyWords[2];
        }
    }


    public void setNewsDate(String date) {
        newsDate = date;
    }

    public void checkKeywords() {
        if (keyWord1 == null || keyWord1.equals("")) {
            keyWord1 = chatKeyWord1 != null ? chatKeyWord1 : null;
        }
        if (keyWord2 == null || keyWord2.equals("")) {
            keyWord2 = chatKeyWord2 != null ? chatKeyWord2 : null;
        }
        if (keyWord3 == null || keyWord3.equals("")) {
            keyWord3 = chatKeyWord3 != null ? chatKeyWord3 : null;
        }

        if (chatKeyWord1 == null || chatKeyWord1.equals("")) {
            chatKeyWord1 = keyWord1 != null ? keyWord1 : null;
        }
        if (chatKeyWord2 == null || chatKeyWord2.equals("")) {
            chatKeyWord2 = keyWord2 != null ? keyWord2 : null;
        }
        if (chatKeyWord3 == null || chatKeyWord3.equals("")) {
            chatKeyWord3 = keyWord3 != null ? keyWord3 : null;
        }
    }
}
