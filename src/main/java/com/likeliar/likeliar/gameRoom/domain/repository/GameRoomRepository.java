package com.likeliar.likeliar.gameRoom.domain.repository;

import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {

}
