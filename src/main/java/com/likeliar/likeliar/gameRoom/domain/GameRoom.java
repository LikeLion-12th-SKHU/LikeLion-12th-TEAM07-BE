package com.likeliar.likeliar.gameRoom.domain;

import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.global.entity.BaseEntity;
import com.likeliar.likeliar.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//roomName, playerNumber, time, subject, content
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    private String roomName;

    private Long playerNumber;

    private String subject;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member leader;

    @Builder
    private GameRoom(String roomName, Long playerNumber, String subject, String content, Member member) {
        this.roomName = roomName;
        this.playerNumber = playerNumber;
        this.subject = subject;
        this.content = content;
        this.leader = member;
    }

    public void update(GameRoomUpdateReqDto gameRoomUpdateReqDto) {
        this.roomName = gameRoomUpdateReqDto.roomName();
        this.playerNumber = gameRoomUpdateReqDto.playerNumber();
        this.subject = gameRoomUpdateReqDto.subject();
        this.content = gameRoomUpdateReqDto.content();
    }

}
