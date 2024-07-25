package com.likeliar.likeliar.word.api.dto;


import com.likeliar.likeliar.common.dto.BaseResponse;
import com.likeliar.likeliar.common.error.SuccessCode;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomSaveReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.request.GameRoomUpdateReqDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomInfoResDto;
import com.likeliar.likeliar.gameRoom.api.dto.response.GameRoomListResDto;
import com.likeliar.likeliar.gameRoom.application.GameRoomService;
import com.likeliar.likeliar.word.api.dto.response.WordInfoResDto;
import com.likeliar.likeliar.word.api.dto.response.WordListResDto;
import com.likeliar.likeliar.word.application.WordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService){
        this.wordService = wordService;
    }


    //단어 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<WordListResDto> WordFindAll(){
        WordListResDto wordListResDto = wordService.wordFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, wordListResDto);
    }


    //단어Id에 따라 단어 한 개 조회
    @GetMapping("/{wordsId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<WordInfoResDto> wordFindById(@PathVariable("wordsId") Long wordId){
        WordInfoResDto wordInfoResDto = wordService.wordFindById(wordId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, wordInfoResDto);
    }

}
