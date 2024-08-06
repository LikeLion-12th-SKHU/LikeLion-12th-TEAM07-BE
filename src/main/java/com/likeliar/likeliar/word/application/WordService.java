package com.likeliar.likeliar.word.application;

import com.likeliar.likeliar.word.api.dto.request.WordSaveReqDto;
import com.likeliar.likeliar.word.api.dto.response.WordInfoResDto;
import com.likeliar.likeliar.word.api.dto.response.WordListResDto;
import com.likeliar.likeliar.word.domain.Word;
import com.likeliar.likeliar.word.domain.repository.WordRepository;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Transactional
    public void save(WordSaveReqDto wordSaveReqDto) {
        Word word = Word.builder()
                .subject(wordSaveReqDto.subject())
                .vocabulary(wordSaveReqDto.vocabulary())
                .description(wordSaveReqDto.description())
                .build();

        wordRepository.save(word);
    }

    public WordListResDto wordFindAll() {
        List<Word> words = wordRepository.findAll();

        List<WordInfoResDto> wordInfoResDtoList = words.stream()
                .map(WordInfoResDto::from)
                .toList();

        return WordListResDto.from(wordInfoResDtoList);
    }

    // 주제에 따른 단어 랜덤으로 한개 가져오기
    public WordInfoResDto wordFindById(String subject) {
        List<Word> words = wordRepository.findBySubject(subject);

        Random random = new Random();
        Word randomWord = words.get(random.nextInt(words.size()));

        return WordInfoResDto.from(randomWord);
    }

}
