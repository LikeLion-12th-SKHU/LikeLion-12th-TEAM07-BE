package com.likeliar.likeliar.gameRoom.api.dto;

import com.likeliar.likeliar.common.dto.BaseResponse;
import com.likeliar.likeliar.common.error.SuccessCode;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.application.GameRoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class GameRoomController {
    private final GameRoomService gameRoomService;

    public GameRoomController(GameRoomService gameRoomService){
        this.gameRoomService = gameRoomService;
    }

    //게임방 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<GameRoomInfoResDto> gameRoomSave(@RequestBody @Valid GameRoomSaveReqDto gameRoomSaveReqDto){
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomSave(gameRoomSaveReqDto);
        return BaseResponse.success(SuccessCode.GAMEROOM_SAVE_SUCCESS, gameRoomInfoResDto);
    }

    //게임방 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomListResDto> GameRoomFindAll(){
        GameRoomListResDto gameRoomListResDto = gameRoomService.gameRoomFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameRoomListResDto);
    }


   //게임방Id에 따라 게임방 한 개 조회
   @GetMapping("/{roomsId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomInfoResDto> gameRoomFindById(@PathVariable("roomsId") Long gameRoomId){
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomFindById(gameRoomId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameRoomInfoResDto);
    }


    //게임룸 수정
    @PatchMapping("/rooms/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomInfoResDto> gameRoomUpdate(@PathVariable("gameId") Long gameRoomId,
                                                           @RequestBody @Valid GameRoomUpdateReqDto gameRoomUpdateReqDto) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomUpdate(gameRoomId, gameRoomUpdateReqDto);
        return BaseResponse.success(SuccessCode.GAMEROOM_UPDATE_SUCCESS, gameRoomInfoResDto);
    }

    //게임룸 삭제
    @DeleteMapping("/rooms/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomInfoResDto> gameRoomDelete(@PathVariable("gameId") Long gameRoomId){
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomDelete(gameRoomId);
        return BaseResponse.success(SuccessCode.GAMEROOM_DELETE_SUCCESS, gameRoomInfoResDto);
    }
}
