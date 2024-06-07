package com.clova.issuecream.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {

    OK("정상처리 완료"),
    error("에러 발생"),
    incorrect("잘못된 방식")
            ;

    private final String message;
}
