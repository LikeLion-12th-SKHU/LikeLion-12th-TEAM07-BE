package com.likeliar.likeliar.gameRoom.application;

import com.likeliar.likeliar.common.error.ErrorCode;
import com.likeliar.likeliar.common.exception.NotFoundException;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.domain.GameRoom;
import com.likeliar.likeliar.gameRoom.domain.GameRoomMemberMapping;
import com.likeliar.likeliar.gameRoom.domain.repository.GameRoomMemberRepository;
import com.likeliar.likeliar.gameRoom.domain.repository.GameRoomRepository;
import com.likeliar.likeliar.member.domain.Member;
import com.likeliar.likeliar.member.domain.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//roomName, playerNumber, time, subject, content
@Service
@Transactional(readOnly = true)
public class GameRoomService {
    private final MemberRepository memberRepository;
    private final GameRoomRepository gameRoomRepository;
    private final GameRoomMemberRepository gameRoomMemberRepository;

    public GameRoomService(MemberRepository memberRepository, GameRoomRepository gameRoomRepository,
                           GameRoomMemberRepository gameRoomMemberRepository) {
        this.memberRepository = memberRepository;
        this.gameRoomRepository = gameRoomRepository;
        this.gameRoomMemberRepository = gameRoomMemberRepository;
    }

    @Transactional
    public GameRoomInfoResDto gameRoomSave(String email, GameRoomSaveReqDto gameRoomSaveReqDto) {
        Member member = getMemberByEmail(email);

        GameRoom gameRoom = GameRoom.builder()
                .roomName(gameRoomSaveReqDto.roomName())
                .playerNumber(gameRoomSaveReqDto.playerNumber())
                .subject(gameRoomSaveReqDto.subject())
                .content(gameRoomSaveReqDto.content())
                .member(member)
                .build();

        gameRoomRepository.save(gameRoom);
        return GameRoomInfoResDto.of(member, gameRoom);
    }

    // Read All
    public GameRoomListResDto gameRoomFindAll(String email) {
        Member member = getMemberByEmail(email);
        List<GameRoom> gameRooms = gameRoomRepository.findAll();

        List<GameRoomInfoResDto> gameRoomInfoResDtoList = gameRooms.stream()
                .map(gameRoom -> {
                    List<GameRoomMemberMapping> gameRoomMappings = gameRoomMemberRepository.findByGameRoom(gameRoom);
                    return GameRoomInfoResDto.of(member, gameRoom, gameRoomMappings);
                })
                .toList();

        return GameRoomListResDto.from(gameRoomInfoResDtoList);
    }

    // Read One (게임방id에 따른 게임방 하나 조회)
    public GameRoomInfoResDto gameRoomFindById(String email, Long gameRoomId) {
        Member member = getMemberByEmail(email);
        GameRoom gameRoom = getGameRoomById(gameRoomId);

        List<GameRoomMemberMapping> gameRoomMappings = gameRoomMemberRepository.findByGameRoom(gameRoom);

        return GameRoomInfoResDto.of(member, gameRoom, gameRoomMappings);
    }

    //Update
    @Transactional
    public GameRoomInfoResDto gameRoomUpdate(String email, Long gameRoomId, GameRoomUpdateReqDto gameRoomUpdateReqDto) {
        Member member = getMemberByEmail(email);
        GameRoom gameRoom = getGameRoomById(gameRoomId);
        List<GameRoomMemberMapping> gameRoomMappings = gameRoomMemberRepository.findByGameRoom(gameRoom);

        // 게임 장이랑 본인과 일치하는지 예외처리

        gameRoom.update(gameRoomUpdateReqDto);
        return GameRoomInfoResDto.of(member, gameRoom, gameRoomMappings);
    }

    // Delete
    @Transactional
    public void gameRoomDelete(String email, Long gameRoomId) {
        Member member = getMemberByEmail(email);
        GameRoom gameRoom = getGameRoomById(gameRoomId);

        // 게임 장이랑 본인과 일치하는지 예외처리

        gameRoomRepository.delete(gameRoom);
    }

    // 게임 방 참여하기
    @Transactional
    public void joinGame(String email, Long gameRoomId) {
        Member member = getMemberByEmail(email);
        GameRoom gameRoom = getGameRoomById(gameRoomId);

        gameRoomMemberRepository.save(GameRoomMemberMapping.builder()
                .member(member)
                .gameRoom(gameRoom)
                .build());
    }

    // 게임 방 나가기
    @Transactional
    public void exitGame(String email, Long gameRoomId) {
        Member member = getMemberByEmail(email);
        GameRoom gameRoom = getGameRoomById(gameRoomId);

        GameRoomMemberMapping gameRoomMemberMapping = gameRoomMemberRepository.findByGameRoomAndMember(gameRoom, member)
                .orElseThrow(
                        () -> new NotFoundException(ErrorCode.GAMEROOMS_MEMBER_MAPPING_NOT_FOUND_EXCEPTION,
                                ErrorCode.GAMEROOMS_MEMBER_MAPPING_NOT_FOUND_EXCEPTION.getMessage()
                                        + email)
                );

        gameRoomMemberRepository.delete(gameRoomMemberMapping);
    }

    // 게임 끝난 후 점수와 판수를 올려주는 로직

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.MEMBERS_NOT_FOUND_EXCEPTION.getMessage()
                                + email)
        );
    }

    private GameRoom getGameRoomById(Long gameRoomId) {
        return gameRoomRepository.findById(gameRoomId).orElseThrow(
                () -> new NotFoundException(ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION,
                        ErrorCode.GAMEROOMS_NOT_FOUND_EXCEPTION.getMessage()
                                + gameRoomId)
        );
    }

}
