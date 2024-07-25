package com.likeliar.likeliar.word.domain;


import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
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

    private String topic; //주제
    private String vocabulary; //단어
    private String explanation; //단어 뜻, 설명

    @Builder
    private Word(String topic, String vocabulary, String explanation){
        this.topic = topic;
        this.vocabulary = vocabulary;
        this.explanation = explanation;
    }

}
