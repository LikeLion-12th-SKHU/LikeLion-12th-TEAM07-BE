package com.likeliar.likeliar.gameRoom.api.dto.response;

import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.gameRoom.domain.GameRoomMemberMapping;
import com.likeliar.likeliar.member.domain.Member;
import java.util.List;
import lombok.Builder;

@Builder
public record GameRoomInfoResDto(
        Long myMemberId,
        Long leaderId,
        String roomName,
        Long playerNumber,
        String subject,
        String content,
        List<String> joinMemberNames
) {
    public static GameRoomInfoResDto of(Member member, GameRoom gameRoom) {
        return GameRoomInfoResDto.builder()
                .myMemberId(member.getId())
                .leaderId(gameRoom.getLeader().getId())
                .roomName(gameRoom.getRoomName())
                .playerNumber(gameRoom.getPlayerNumber())
                .subject(gameRoom.getSubject())
                .content(gameRoom.getContent())
                .build();
    }

    public static GameRoomInfoResDto of(Member member, GameRoom gameRoom,
                                        List<GameRoomMemberMapping> gameRoomMemberMappingList) {
        List<String> joinMemberNames = gameRoomMemberMappingList.stream()
                .map(g -> g.getMember().getName())
                .toList();

        return GameRoomInfoResDto.builder()
                .myMemberId(member.getId())
                .leaderId(gameRoom.getLeader().getId())
                .roomName(gameRoom.getRoomName())
                .playerNumber(gameRoom.getPlayerNumber())
                .subject(gameRoom.getSubject())
                .content(gameRoom.getContent())
                .joinMemberNames(joinMemberNames)
                .build();
    }
}
