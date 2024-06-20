package com.clova.issuecream.contents.dto;

import com.clova.issuecream.contents.enums.CategoryCode;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDetailDto {
    String newsTitle;
    String newsContent;
    String newsChatContent;
    CategoryCode categoryCode;
    String newsDate;
    String keyWord1;
    String keyWord2;
    String keyWord3;

    public String getNewsDate() {
        if (newsDate == null || newsDate.equals("")) {
            return "";
        }
        newsDate = newsDate.replaceAll("am", "");
        newsDate = newsDate.replaceAll("pm", "");
        return LocalDate.parse(newsDate, DateTimeFormatter.ofPattern("yyyyMMdd")).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public String getKeyWord1() {
        if(keyWord1 == null || keyWord1.equals("")) {
            return null;
        }
        return "#"+keyWord1;
    }

    public String getKeyWord2() {
        if(keyWord2 == null || keyWord2.equals("")) {
            return null;
        }
        return "#"+keyWord2;
    }

    public String getKeyWord3() {
        if(keyWord3 == null || keyWord3.equals("")) {
            return null;
        }
        return "#"+keyWord3;
    }
}
