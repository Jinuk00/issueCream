package com.clova.issuecream.contents.enums;

import com.clova.issuecream.common.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum CategoryCode implements Constant {

    It("IT","IT"),
    시사("PRV","시사"),
    미디어("MD","미디어"),
    스포츠("SPR","스포츠"),
    경제("ECN","경제"),
    ;
    private final String code;
    private final String text;

    public static CategoryCode of(String code) {
        if(code == null || "".equals(code)){
            return null;
        }
        return Stream.of(CategoryCode.values())
                .filter(f -> f.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static CategoryCode transByName(String categoryNm) {
       return Arrays.stream(CategoryCode.values())
                .filter(i -> i.getText().equals(categoryNm))
                .findAny().orElse(null);
    }
}
