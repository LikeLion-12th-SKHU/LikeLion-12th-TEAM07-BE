package com.likeliar.likeliar.member.domain.repository;

import com.likeliar.likeliar.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberCustomRepository {
    Page<Member> findByRankingMember(Pageable pageable);
}
