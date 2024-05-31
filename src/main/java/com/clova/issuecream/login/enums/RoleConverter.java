package com.clova.issuecream.login.enums;


import com.clova.issuecream.common.Constant;
import com.clova.issuecream.common.EnumConverter;

public class RoleConverter extends EnumConverter<Role,String> {
    @Override
    public Constant convertToEntityAttribute(String dbData) {
        return Role.of(dbData);
    }

}
