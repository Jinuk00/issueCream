package com.clova.issuecream.contents.dto;

import com.clova.issuecream.contents.enums.CategoryCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsTitleDto {
    Long id;
    String newsTitle;
    String newsDate;
    CategoryCode categoryCode;

    public String getNewsDate() {
        if (newsDate == null || newsDate.equals("")) {
            return "";
        }
        newsDate = newsDate.replaceAll("am", "");
        newsDate = newsDate.replaceAll("pm", "");
        return LocalDate.parse(newsDate, DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
