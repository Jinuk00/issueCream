package com.clova.issuecream.contents.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDto {
    Long boardId;
    String newsTitle;

}
