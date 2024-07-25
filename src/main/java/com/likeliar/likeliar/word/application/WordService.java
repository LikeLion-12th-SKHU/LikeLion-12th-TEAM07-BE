package com.likeliar.likeliar.word.application;

import com.likeliar.likeliar.common.error.ErrorCode;
import com.likeliar.likeliar.common.exception.NotFoundException;
import com.likeliar.likeliar.word.api.dto.response.WordInfoResDto;
import com.likeliar.likeliar.word.api.dto.response.WordListResDto;
import com.likeliar.likeliar.word.domain.Word;
import com.likeliar.likeliar.word.domain.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    public WordListResDto wordFindAll() {
        List<Word> words = wordRepository.findAll();

        List<WordInfoResDto> wordInfoResDtoList = words.stream()
                .map(WordInfoResDto::from)
                .toList();

        return WordListResDto.from(wordInfoResDtoList);
    }

    // Read One (단어 id에 따른 단어 하나 조회)
    public WordInfoResDto wordFindById(Long wordId) {
        Word word = wordRepository.findById(wordId).orElseThrow(
                () -> new NotFoundException(ErrorCode.WORDS_NOT_FOUND_EXCEPTION,
                        ErrorCode.WORDS_NOT_FOUND_EXCEPTION.getMessage()
                                + wordId)
        );

        return WordInfoResDto.from(word);
    }
}
