package com.clova.issuecream.login.entity;

import com.clova.issuecream.login.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    long id;
    String name;
    String username; //id
    String email;
    Role role;


    @Builder
    public Member(long id, String name, String username, String email, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
