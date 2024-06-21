package com.clova.issuecream.news.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsTransDto {
    String title;
    String category;
    String content_chat;
    String content_smry;
}
