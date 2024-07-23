package com.likeliar.likeliar.gameRoom.domain;

import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
 //roomName, playerNumber, time, subject, content
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    private String roomName;
    private long playerNumber;
    private long time;
    private String subject;
    private String content;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @joinColumn(name="member_id")
    private Member member;
     */

     @Builder
     private GameRoom(String roomName, Long playerNumber, Long time, String subject, String content){
         this.roomName = roomName;
         this.playerNumber = playerNumber;
         this.time = time;
         this.subject = subject;
         this.content = content;
     }

     public void update(GameRoomUpdateReqDto gameRoomUpdateReqDto){
         this.roomName = gameRoomUpdateReqDto.roomName();
         this.playerNumber = gameRoomUpdateReqDto.playerNumber();
         this.time = gameRoomUpdateReqDto.time();
         this.subject = gameRoomUpdateReqDto.subject();
         this.content = gameRoomUpdateReqDto.content();
     }

}
