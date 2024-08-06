package com.likeliar.likeliar.word.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String description;

    @Builder
    private Word(String subject, String vocabulary, String description) {
        this.subject = subject;
        this.vocabulary = vocabulary;
        this.description = description;
    }

}
