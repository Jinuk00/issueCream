package com.clova.issuecream.common;

import jakarta.persistence.AttributeConverter;

public abstract class CommonEnumConverter<X, Y> implements AttributeConverter<Constant, String> {
    @Override
    public String convertToDatabaseColumn(Constant attribute) {
        return attribute == null ? "" : attribute.getCode();
    }
}
