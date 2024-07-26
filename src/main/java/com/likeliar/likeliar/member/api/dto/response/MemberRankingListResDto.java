package com.likeliar.likeliar.member.api.dto.response;

import com.likeliar.likeliar.global.dto.PageInfoResDto;
import java.util.List;
import lombok.Builder;

@Builder
public record MemberRankingListResDto(
        List<MemberInfoResDto> memberInfoResDtos,
        PageInfoResDto pageInfoResDto
) {
    public static MemberRankingListResDto of(List<MemberInfoResDto> memberInfoResDto, PageInfoResDto pageInfoResDto) {
        return MemberRankingListResDto.builder()
                .memberInfoResDtos(memberInfoResDto)
                .pageInfoResDto(pageInfoResDto)
                .build();
    }
}
