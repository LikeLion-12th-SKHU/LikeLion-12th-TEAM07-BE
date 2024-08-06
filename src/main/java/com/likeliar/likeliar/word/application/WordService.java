package com.likeliar.likeliar.word.application;

import com.likeliar.likeliar.word.api.dto.request.WordSaveReqDto;
import com.likeliar.likeliar.word.api.dto.response.WordInfoResDto;
import com.likeliar.likeliar.word.api.dto.response.WordListResDto;
import com.likeliar.likeliar.word.domain.Word;
import com.likeliar.likeliar.word.domain.repository.WordRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    public void save(List<WordSaveReqDto> wordSaveReqDto) {
        for (WordSaveReqDto saveReqDto : wordSaveReqDto) {
            Word word = Word.builder()
                    .subject(saveReqDto.subject())
                    .vocabulary(saveReqDto.vocabulary())
                    .description(saveReqDto.description())
                    .build();

            wordRepository.save(word);
        }

    }

    public List<WordListResDto> wordFindAll() {
        List<Word> words = wordRepository.findAll();
        List<WordListResDto> wordsBySubject = new ArrayList<>();

        Set<String> subjects = new HashSet<>();
        words.stream().map(Word::getSubject).forEach(subjects::add);

        for (String subject : subjects) {
            List<Word> wordBySubject = wordRepository.findBySubject(subject);

            List<WordInfoResDto> wordInfoResDtoList = wordBySubject.stream()
                    .map(WordInfoResDto::from)
                    .toList();

            WordListResDto from = WordListResDto.from(subject, wordInfoResDtoList);

            wordsBySubject.add(from);
        }

        return wordsBySubject;
    }

    // 주제에 따른 단어 랜덤으로 한개 가져오기
    public WordInfoResDto wordFindById(String subject) {
        List<Word> words = wordRepository.findBySubject(subject);

        Random random = new Random();
        Word randomWord = words.get(random.nextInt(words.size()));

        return WordInfoResDto.from(randomWord);
    }

}
