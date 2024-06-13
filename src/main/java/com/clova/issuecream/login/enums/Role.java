package com.clova.issuecream.login.enums;

import com.clova.issuecream.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    String code;

    public static Role of(String code) {
        if (code == null || code.equals("")) {
            return null;
        }
        return Arrays.stream(Role.values())
                .filter(i -> i.getCode().equals(code))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}
