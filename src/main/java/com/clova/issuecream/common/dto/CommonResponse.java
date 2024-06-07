package com.clova.issuecream.common.dto;

import com.clova.issuecream.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class CommonResponse {
    private String resultCode;
    private String message;
    private Object data;

    public static CommonResponse ok() {
        return CommonResponse.builder().resultCode(ResultCode.OK.name())
                .message(ResultCode.OK.getMessage()).build();
    }

    public static CommonResponse ok(String message) {
        return CommonResponse.builder().resultCode(ResultCode.OK.name())
                .message(message.equals("") ? ResultCode.OK.getMessage() : message).build();
    }

    public static CommonResponse okData(Object data) {
        return CommonResponse.builder().resultCode(ResultCode.OK.name())
                .message(ResultCode.OK.getMessage())
                .data(data).build();
    }

    public static CommonResponse okData(String message, Object data) {
        return CommonResponse.builder().resultCode(ResultCode.OK.name())
                .message(message.equals("") ? ResultCode.OK.getMessage() : message)
                .data(data).build();
    }

    public static CommonResponse error(String code, String message) {
        return CommonResponse.builder().resultCode(ResultCode.error.name())
                .message(message)
                .build();
    }

    public static CommonResponse error(String code, String message, Object data) {
        return CommonResponse.builder().resultCode(ResultCode.error.name())
                .message(message).data(data)
                .build();
    }

    public static CommonResponse incorrect(String message) {
        return CommonResponse.builder().resultCode(ResultCode.incorrect.name()).message(message).build();
    }
}
