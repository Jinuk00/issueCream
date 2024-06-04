package com.clova.issuecream.login.enums;

import com.clova.issuecream.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role implements Constant{
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    String code;

    public static Role of(String code) {
        return Constant.fromCode(Role.class, code);
    }

    @Override
    public String getText() {
        return this.name();
    }
}
