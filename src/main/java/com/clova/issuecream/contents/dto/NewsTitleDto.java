package com.clova.issuecream.contents.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsTitleDto {
    Long id;
    String newsTitle;
}
