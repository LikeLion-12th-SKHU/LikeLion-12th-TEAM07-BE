package com.likeliar.likeliar.auth.api;

import com.likeliar.likeliar.auth.api.dto.request.RefreshTokenReqDto;
import com.likeliar.likeliar.auth.api.dto.request.TokenReqDto;
import com.likeliar.likeliar.auth.api.dto.response.MemberLoginResDto;
import com.likeliar.likeliar.auth.api.dto.response.UserInfo;
import com.likeliar.likeliar.auth.application.AuthMemberService;
import com.likeliar.likeliar.auth.application.AuthService;
import com.likeliar.likeliar.auth.application.AuthServiceFactory;
import com.likeliar.likeliar.auth.application.TokenService;
import com.likeliar.likeliar.global.jwt.api.dto.TokenDto;
import com.likeliar.likeliar.global.template.RspTemplate;
import com.likeliar.likeliar.member.domain.SocialType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceFactory authServiceFactory;
    private final AuthMemberService memberService;
    private final TokenService tokenService;

    @GetMapping("/{provider}/id-token")
    public RspTemplate<String> getIdToken(
            @PathVariable(name = "provider") String provider,
            @RequestParam(name = "code") String code) {
        AuthService authService = authServiceFactory.getAuthService(provider);
        String idToken = authService.getIdToken(code);

        return new RspTemplate<>(HttpStatus.OK, "IdToken", idToken);
    }

    @Operation(summary = "로그인 후 토큰 발급", description = "액세스, 리프레쉬 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 발급 성공")
    })
    @PostMapping("/{provider}/token")
    public RspTemplate<TokenDto> generateAccessAndRefreshToken(
            @Parameter(name = "provider", description = "소셜 타입(google, kakao)", in = ParameterIn.PATH)
            @PathVariable(name = "provider") String provider,
            @RequestBody TokenReqDto tokenReqDto) {
        AuthService authService = authServiceFactory.getAuthService(provider);
        UserInfo userInfo = authService.getUserInfo(tokenReqDto.authCode());

        MemberLoginResDto getMemberDto = memberService.saveUserInfo(userInfo,
                SocialType.valueOf(provider.toUpperCase()));
        TokenDto getToken = tokenService.getToken(getMemberDto);

        return new RspTemplate<>(HttpStatus.OK, "토큰 발급", getToken);
    }

    @Operation(summary = "액세스 토큰 재발급", description = "리프레쉬 토큰으로 액세스 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 발급 성공")
    })
    @PostMapping("/token/access")
    public RspTemplate<TokenDto> generateAccessToken(@RequestBody RefreshTokenReqDto refreshTokenReqDto) {
        TokenDto getToken = tokenService.generateAccessToken(refreshTokenReqDto);

        return new RspTemplate<>(HttpStatus.OK, "액세스 토큰 발급", getToken);
    }

}
