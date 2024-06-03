package com.clova.issuecream.login.repository;

import com.clova.issuecream.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
