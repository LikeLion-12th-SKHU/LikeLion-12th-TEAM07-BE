package com.likeliar.likeliar.word.domain.repository;

import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
