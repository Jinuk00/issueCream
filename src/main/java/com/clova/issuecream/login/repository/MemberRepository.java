package com.clova.issuecream.login.repository;

import com.clova.issuecream.login.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member findByEmailAndProvider(String emial, String provider);
}
