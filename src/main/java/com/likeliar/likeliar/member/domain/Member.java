package com.likeliar.likeliar.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String name;

    private String picture;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String nickname;

    private int score;

    private int gameRounds;

    @Builder
    private Member(Role role, String email, String name, String picture, SocialType socialType, String nickname) {
        this.role = role;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.socialType = socialType;
        this.nickname = nickname;
        this.score = 0;
        this.gameRounds = 0;
    }

    public void endGame(int score) {
        this.score += score;
        this.gameRounds++;
    }

}
