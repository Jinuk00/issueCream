package com.clova.issuecream.contents.enums;

import com.clova.issuecream.common.Constant;
import com.clova.issuecream.common.CommonEnumConverter;
import jakarta.persistence.AttributeConverter;

public class CategoryCodeConverter implements AttributeConverter<CategoryCode, String> {

    @Override
    public String convertToDatabaseColumn(CategoryCode categoryCode) {
        if(categoryCode == null) {
            return null;
        }
        return categoryCode.getCode();
    }

    @Override
    public CategoryCode convertToEntityAttribute(String dbData) {
        return CategoryCode.of(dbData);
    }
}
