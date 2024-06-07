package com.clova.issuecream.common.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.common.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionCommonController {
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse userException(UserException e) {
        log.info("에러 {}",CommonResponse.error(e.getCode(), e.getMessage(), e.getData()).toString());
        return CommonResponse.error(e.getCode(), e.getMessage(), e.getData());
    }
}
