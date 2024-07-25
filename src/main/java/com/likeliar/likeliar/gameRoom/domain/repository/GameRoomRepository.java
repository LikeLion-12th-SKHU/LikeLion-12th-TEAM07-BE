package com.likeliar.likeliar.gameRoom.domain.repository;

import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
    //List<GameRoom> findByMember(Member member);

}
