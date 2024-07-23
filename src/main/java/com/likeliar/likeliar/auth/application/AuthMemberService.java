package com.likeliar.likeliar.auth.application;

import com.likeliar.likeliar.auth.api.dto.response.MemberLoginResDto;
import com.likeliar.likeliar.auth.api.dto.response.UserInfo;
import com.likeliar.likeliar.auth.exception.ExistsMemberEmailException;
import com.likeliar.likeliar.auth.exception.NotFoundGithubEmailException;
import com.likeliar.likeliar.member.domain.Member;
import com.likeliar.likeliar.member.domain.Role;
import com.likeliar.likeliar.member.domain.SocialType;
import com.likeliar.likeliar.member.domain.repository.MemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthMemberService {
    private final MemberRepository memberRepository;

    public AuthMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberLoginResDto saveUserInfo(UserInfo userInfo, SocialType provider) {
        Member member = getExistingMemberOrCreateNew(userInfo, provider);

        validateSocialType(member, provider);

        return MemberLoginResDto.from(member);
    }

    private Member getExistingMemberOrCreateNew(UserInfo userInfo, SocialType provider) {
        return memberRepository.findByEmail(userInfo.email()).orElseGet(() -> createMember(userInfo, provider));
    }

    private Member createMember(UserInfo userInfo, SocialType provider) {
        String userPicture = getUserPicture(userInfo.picture());

        return memberRepository.save(
                Member.builder()
                        .email(userInfo.email())
                        .name(userInfo.name())
                        .picture(userPicture)
                        .socialType(provider)
                        .role(Role.ROLE_USER)
                        .build()
        );
    }

    private String getUserPicture(String picture) {
        return Optional.ofNullable(picture)
                .map(this::convertToHighRes)
                .orElseThrow(null);
    }

    private String convertToHighRes(String url){
        return url.replace("s96-c", "s2048-c");
    }

    private void validateSocialType(Member member, SocialType provider) {
        if (!provider.equals(member.getSocialType())) {
            throw new ExistsMemberEmailException();
        }
    }

}
