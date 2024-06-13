package com.clova.issuecream.login.enums;


import jakarta.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role,String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public Role convertToEntityAttribute(String code) {
        return Role.of(code);
    }

}
