package com.likeliar.likeliar.gameRoom.application;

import com.likeliar.likeliar.common.error.ErrorCode;
import com.likeliar.likeliar.common.exception.NotFoundException;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.gameRoom.domain.repository.GameRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//roomName, playerNumber, time, subject, content
@Service
@Transactional(readOnly = true)
public class GameRoomService {
    //private final MemberRepository memberRepository;
    private final GameRoomRepository gameRoomRepository;

    public GameRoomService(GameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }


    @Transactional
    public GameRoomInfoResDto gameRoomSave(GameRoomSaveReqDto gameRoomSaveReqDto){
        /* Member member = memberRepository.findById(gameRoomSaveReqDto.memberId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()
                                +gameRoomSaveReqDto.memberId())
        ); */

        GameRoom gameRoom = GameRoom.builder()
                .roomName(gameRoomSaveReqDto.roomName())
                .playerNumber(gameRoomSaveReqDto.playerNumber())
                .time(gameRoomSaveReqDto.time())
                .subject(gameRoomSaveReqDto.subject())
                .content(gameRoomSaveReqDto.content())
                .build();

        gameRoomRepository.save(gameRoom);
        return GameRoomInfoResDto.from(gameRoom);
    }

    // Read All
    public GameRoomListResDto gameRoomFindAll() {
        List<GameRoom> gameRooms = gameRoomRepository.findAll();

        List<GameRoomInfoResDto> gameRoomInfoResDtoList = gameRooms.stream()
                .map(GameRoomInfoResDto::from)
                .toList();

        return GameRoomListResDto.from(gameRoomInfoResDtoList);
    }

    // Read One (게임방id에 따른 게임방 하나 조회)
    public GameRoomInfoResDto gameRoomFindById(Long gameRoomId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION.getMessage()
                                + gameRoomId)
        );

        return GameRoomInfoResDto.from(gameRoom);
    }

    //Update
    @Transactional
    public GameRoomInfoResDto gameRoomUpdate(Long gameRoomId, GameRoomUpdateReqDto gameRoomUpdateReqDto) {
            GameRoom gameRoom= gameRoomRepository.findById(gameRoomId).orElseThrow(
                    () -> new NotFoundException(ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION,
                            ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION.getMessage()
                                    + gameRoomId)
            );

            gameRoom.update(gameRoomUpdateReqDto);
            return GameRoomInfoResDto.from(gameRoom);
        }

    // Delete
    public GameRoomInfoResDto gameRoomDelete(Long gameRoomId) {
        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION.getMessage()
                                + gameRoomId)
        );

        gameRoomRepository.delete(gameRoom);
        return GameRoomInfoResDto.from(gameRoom);
    }


}
