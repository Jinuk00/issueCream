package com.clova.issuecream.contents.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "bookmark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamicUpdate
public class Bookmark {

    @Id
    @Column(name = "seq")
    @Comment("시퀀스")
    Long seq;

    @Column(name = "user_id")
    @Comment("사용자 아이디")
    String userId;

    @Column(name = "board_id")
    @Comment("게시물 아이디")
    Long boardId;
}
