package com.likeliar.likeliar.word.domain.repository;

import com.likeliar.likeliar.word.domain.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findBySubject(String subject);
}
