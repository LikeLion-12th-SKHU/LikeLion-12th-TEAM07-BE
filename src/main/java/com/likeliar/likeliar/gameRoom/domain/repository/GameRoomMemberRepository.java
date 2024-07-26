package com.likeliar.likeliar.gameRoom.domain.repository;

import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.gameRoom.domain.GameRoomMemberMapping;
import com.likeliar.likeliar.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomMemberRepository extends JpaRepository<GameRoomMemberMapping, Long> {
    List<GameRoomMemberMapping> findByGameRoom(GameRoom gameRoom);

    Optional<GameRoomMemberMapping> findByGameRoomAndMember(GameRoom gameRoom, Member member);
}
