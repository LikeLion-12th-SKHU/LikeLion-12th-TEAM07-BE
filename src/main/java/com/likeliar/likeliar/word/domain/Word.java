package com.likeliar.likeliar.word.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long wordId; //단어 ID

    private String subject; //주제
    private String vocabulary; //단어
    private String explanation; //단어 뜻, 설명

    @Builder
    private Word(String subject, String vocabulary, String explanation){
        this.subject = subject;
        this.vocabulary = vocabulary;
        this.explanation = explanation;
    }

}
