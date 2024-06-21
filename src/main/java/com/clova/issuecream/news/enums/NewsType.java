package com.clova.issuecream.news.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsType {
    요약형("smry"),
    대화형("chat"),
    ;

    private final String code;
}
