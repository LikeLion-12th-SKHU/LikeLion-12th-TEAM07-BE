package com.likeliar.likeliar.member.domain.repository;

import static com.likeliar.likeliar.member.domain.QMember.member;

import com.likeliar.likeliar.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory queryFactory;

    public MemberCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Member> findByRankingMember(Pageable pageable) {
        long total = queryFactory
                .selectFrom(member)
                .stream()
                .count();

        List<Member> members = queryFactory
                .selectFrom(member)
                .orderBy(member.score.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(members, pageable, total);
    }
    
}
