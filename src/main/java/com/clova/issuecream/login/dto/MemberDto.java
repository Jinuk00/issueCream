package com.clova.issuecream.login.dto;

import com.clova.issuecream.login.entity.Member;
import com.clova.issuecream.login.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto{
    String name;
    String username;
    String email;
    String nickname;
    String role;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}
