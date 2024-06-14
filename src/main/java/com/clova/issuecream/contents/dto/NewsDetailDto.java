package com.clova.issuecream.contents.dto;

import com.clova.issuecream.contents.enums.CategoryCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDetailDto {
    String newsTitle;
    String newsContent;
    CategoryCode categoryCode;
}
