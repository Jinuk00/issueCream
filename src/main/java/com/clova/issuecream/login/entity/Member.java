package com.clova.issuecream.login.entity;

import com.clova.issuecream.login.enums.Role;
import com.clova.issuecream.login.enums.RoleConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Member {

    @Id
    @Column(name = "seq")
    @Comment("순번")
    long seq;

    @Column(name = "name")
    @Comment("이름")
    String name;
    @Column(name = "username")
    @Comment("아이디")
    String username; //id
    @Column(name = "email")
    @Comment("이메일")
    String email;
    @Column(name = "role")
    @Comment("권한")
    @Convert(converter = RoleConverter.class)
    Role role;


    @Builder
    public Member(long seq, String name, String username, String email, Role role) {
        this.seq = seq;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
