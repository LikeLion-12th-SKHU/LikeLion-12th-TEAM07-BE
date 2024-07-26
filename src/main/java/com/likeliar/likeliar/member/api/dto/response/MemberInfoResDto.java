package com.likeliar.likeliar.member.api.dto.response;

import com.likeliar.likeliar.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoResDto(
        String name,
        String picture,
        int score,
        int gameRounds
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .picture(member.getPicture())
                .score(member.getScore())
                .gameRounds(member.getGameRounds())
                .build();
    }
}
