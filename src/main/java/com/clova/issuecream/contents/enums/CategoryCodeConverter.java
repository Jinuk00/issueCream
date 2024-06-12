package com.clova.issuecream.contents.enums;

import com.clova.issuecream.common.Constant;
import com.clova.issuecream.common.EnumConverter;

public class CategoryCodeConverter extends EnumConverter<CategoryCode, String> {
    @Override
    public Constant convertToEntityAttribute(String dbData) {
        return CategoryCode.of(dbData);
    }
}
