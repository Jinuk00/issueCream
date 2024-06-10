package com.clova.issuecream.contents.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class NewsBoard {

    @Id
    @Column(name = "id")
    @Comment("id값")
    Long id;

    @Column(name = "board_nm")
    @Comment("뉴스 제목")
    String boardNm;

    @Column(name = "board_content")
    @Comment("뉴스 본문")
    String boardContent;

}
