package com.likeliar.likeliar.gameRoom.api.dto;

import com.likeliar.likeliar.common.dto.BaseResponse;
import com.likeliar.likeliar.common.error.SuccessCode;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.application.GameRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class GameRoomController {
    private final GameRoomService gameRoomService;

    public GameRoomController(GameRoomService gameRoomService) {
        this.gameRoomService = gameRoomService;
    }

    @Operation(summary = "게임 방 생성", description = "게임 방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "생성 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<GameRoomInfoResDto> gameRoomSave(@AuthenticationPrincipal String email,
                                                         @RequestBody @Valid GameRoomSaveReqDto gameRoomSaveReqDto) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomSave(email, gameRoomSaveReqDto);
        return BaseResponse.success(SuccessCode.GAMEROOM_SAVE_SUCCESS, gameRoomInfoResDto);
    }

    @Operation(summary = "게임 방 전체 조회", description = "게임 방을 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomListResDto> GameRoomFindAll(@AuthenticationPrincipal String email) {
        GameRoomListResDto gameRoomListResDto = gameRoomService.gameRoomFindAll(email);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameRoomListResDto);
    }

    @Operation(summary = "게임 방 단건 조회", description = "게임 방을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/{roomsId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomInfoResDto> gameRoomFindById(@AuthenticationPrincipal String email,
                                                             @PathVariable("roomsId") Long gameRoomId) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomFindById(email, gameRoomId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, gameRoomInfoResDto);
    }

    @Operation(summary = "게임 방 수정", description = "게임 방을 수정 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PatchMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<GameRoomInfoResDto> gameRoomUpdate(@AuthenticationPrincipal String email,
                                                           @PathVariable("gameId") Long gameRoomId,
                                                           @RequestBody @Valid GameRoomUpdateReqDto gameRoomUpdateReqDto) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomUpdate(email, gameRoomId, gameRoomUpdateReqDto);
        return BaseResponse.success(SuccessCode.GAMEROOM_UPDATE_SUCCESS, gameRoomInfoResDto);
    }

    @Operation(summary = "게임 방 삭제", description = "게임 방을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> gameRoomDelete(@AuthenticationPrincipal String email,
                                               @PathVariable("gameId") Long gameRoomId) {
        gameRoomService.gameRoomDelete(email, gameRoomId);
        return BaseResponse.success(SuccessCode.GAMEROOM_DELETE_SUCCESS, "게임 방 삭제");
    }

    @Operation(summary = "게임 방 참여", description = "게임 방을 참여합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "참여 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/{gameId}/join")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> joinGame(@AuthenticationPrincipal String email,
                                         @PathVariable("gameId") Long gameRoomId) {
        gameRoomService.joinGame(email, gameRoomId);
        return BaseResponse.success(SuccessCode.GAMEROOM_JOIN_SUCCESS, "게임 방 참여");
    }

    @Operation(summary = "게임 방 나가기", description = "게임 방을 나갑니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나가기 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/{gameId}/exit")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> exitGame(@AuthenticationPrincipal String email,
                                         @PathVariable("gameId") Long gameRoomId) {
        gameRoomService.exitGame(email, gameRoomId);
        return BaseResponse.success(SuccessCode.GAMEROOM_JOIN_SUCCESS, "게임 방 나가기");
    }

    // 게임 끝난 후 점수와 판수를 올려주는 로직

}
