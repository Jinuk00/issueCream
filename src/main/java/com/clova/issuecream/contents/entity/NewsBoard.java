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

    @Column(name = "news_dt")
    @Comment("뉴스 기사 날짜")
    String newsDate;

    public void createContent(String title, String newsContent) {
        this.newsTitle = title;
        this.newsContent = newsContent;
    }
}
