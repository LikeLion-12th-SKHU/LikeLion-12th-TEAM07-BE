package com.likeliar.likeliar.member.api;

import com.likeliar.likeliar.global.template.RspTemplate;
import com.likeliar.likeliar.member.api.dto.response.MemberInfoResDto;
import com.likeliar.likeliar.member.api.dto.response.MemberRankingListResDto;
import com.likeliar.likeliar.member.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "사용자 정보", description = "사용자 정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/info")
    public RspTemplate<MemberInfoResDto> info(@AuthenticationPrincipal String email) {
        MemberInfoResDto info = memberService.info(email);
        return new RspTemplate<>(HttpStatus.OK, "사용자 정보", info);
    }

    @Operation(summary = "랭킹", description = "사용자들의 랭킹을 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/ranking")
    public RspTemplate<MemberRankingListResDto> rakingList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        MemberRankingListResDto memberRankingListResDto = memberService.rankingList(pageable);
        return new RspTemplate<>(HttpStatus.OK, "사용자 랭킹 리스트", memberRankingListResDto);
    }

}
