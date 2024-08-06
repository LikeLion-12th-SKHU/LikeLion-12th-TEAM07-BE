package com.likeliar.likeliar.gameRoom.api.dto;

import com.likeliar.likeliar.gameRoom.api.dto.request.EndGameReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.application.GameRoomService;
import com.likeliar.likeliar.global.template.RspTemplate;
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
    public RspTemplate<GameRoomInfoResDto> gameRoomSave(@AuthenticationPrincipal String email,
                                                        @RequestBody @Valid GameRoomSaveReqDto gameRoomSaveReqDto) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomSave(email, gameRoomSaveReqDto);
        return new RspTemplate<>(HttpStatus.CREATED, "방 생성", gameRoomInfoResDto);
    }

    @Operation(summary = "게임 방 전체 조회", description = "게임 방을 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping
    public RspTemplate<GameRoomListResDto> GameRoomFindAll(@AuthenticationPrincipal String email) {
        GameRoomListResDto gameRoomListResDto = gameRoomService.gameRoomFindAll(email);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 전체 조회", gameRoomListResDto);
    }

    @Operation(summary = "게임 방 단건 조회", description = "게임 방을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/{roomId}")
    public RspTemplate<GameRoomInfoResDto> gameRoomFindById(@AuthenticationPrincipal String email,
                                                            @PathVariable("roomId") Long gameRoomId) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomFindById(email, gameRoomId);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 단건 조회", gameRoomInfoResDto);
    }

    @Operation(summary = "게임 방 수정", description = "게임 방을 수정 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PatchMapping("/{roomId}")
    public RspTemplate<GameRoomInfoResDto> gameRoomUpdate(@AuthenticationPrincipal String email,
                                                          @PathVariable("roomId") Long gameRoomId,
                                                          @RequestBody @Valid GameRoomUpdateReqDto gameRoomUpdateReqDto) {
        GameRoomInfoResDto gameRoomInfoResDto = gameRoomService.gameRoomUpdate(email, gameRoomId, gameRoomUpdateReqDto);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 수정", gameRoomInfoResDto);
    }


    @Operation(summary = "게임 방 삭제", description = "게임 방을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @DeleteMapping("/{roomId}")
    public RspTemplate<Void> gameRoomDelete(@AuthenticationPrincipal String email,
                                            @PathVariable("roomId") Long gameRoomId) {
        gameRoomService.gameRoomDelete(email, gameRoomId);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 삭제");
    }

    @Operation(summary = "게임 방 참여", description = "게임 방을 참여합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "참여 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/{roomId}/join")
    public RspTemplate<Void> joinGame(@AuthenticationPrincipal String email,
                                      @PathVariable("roomId") Long gameRoomId) {
        gameRoomService.joinGame(email, gameRoomId);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 참여");
    }

    @Operation(summary = "게임 방 나가기", description = "게임 방을 나갑니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나가기 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/{roomId}/exit")
    public RspTemplate<Void> exitGame(@AuthenticationPrincipal String email,
                                      @PathVariable("roomId") Long gameRoomId) {
        gameRoomService.exitGame(email, gameRoomId);
        return new RspTemplate<>(HttpStatus.OK, "게임 방 나가기");
    }

    @Operation(summary = "게임 종료", description = "게임이 종료되어 점수와 팜수를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게임 종료"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/end")
    public RspTemplate<Void> endGame(@AuthenticationPrincipal String email,
                                     @RequestBody EndGameReqDto endGameReqDto) {
        gameRoomService.endGame(email, endGameReqDto.score());
        return new RspTemplate<>(HttpStatus.OK, "게임 종료");
    }

}
