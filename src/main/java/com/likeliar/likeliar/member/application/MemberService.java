package com.likeliar.likeliar.member.application;

import com.likeliar.likeliar.global.dto.PageInfoResDto;
import com.likeliar.likeliar.member.api.dto.response.MemberInfoResDto;
import com.likeliar.likeliar.member.api.dto.response.MemberRankingListResDto;
import com.likeliar.likeliar.member.domain.Member;
import com.likeliar.likeliar.member.domain.repository.MemberRepository;
import com.likeliar.likeliar.member.exception.MemberNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 사용자 정보
    public MemberInfoResDto info(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        return MemberInfoResDto.from(member);
    }

    // 참여한 게임방 목록

    // 사용자 랭킹 리스트
    public MemberRankingListResDto rankingList(Pageable pageable) {
        Page<Member> rakingMember = memberRepository.findByRankingMember(pageable);

        List<MemberInfoResDto> memberInfoResDtos = rakingMember.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberRankingListResDto.of(memberInfoResDtos, PageInfoResDto.from(rakingMember));
    }


}
