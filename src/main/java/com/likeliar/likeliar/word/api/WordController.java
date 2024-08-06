package com.likeliar.likeliar.word.api;


import com.likeliar.likeliar.global.template.RspTemplate;
import com.likeliar.likeliar.word.api.dto.request.WordSaveReqDto;
import com.likeliar.likeliar.word.api.dto.response.WordInfoResDto;
import com.likeliar.likeliar.word.api.dto.response.WordListResDto;
import com.likeliar.likeliar.word.application.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/words")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping("/")
    public RspTemplate<Void> save(@RequestBody WordSaveReqDto wordSaveReqDto) {
        wordService.save(wordSaveReqDto);
        return new RspTemplate<>(HttpStatus.OK, "단어 저장!ht");
    }

    @Operation(summary = "단어 전체 조회", description = "단어를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping
    public RspTemplate<WordListResDto> WordFindAll() {
        WordListResDto wordListResDto = wordService.wordFindAll();
        return new RspTemplate<>(HttpStatus.OK, "단어 전체 조회", wordListResDto);
    }

    @Operation(summary = "단어 단건 랜덤 조회", description = "단어를 랜덤으로 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/one")
    public RspTemplate<WordInfoResDto> wordFindById(@RequestParam("subject") String subject) {
        WordInfoResDto wordInfoResDto = wordService.wordFindById(subject);
        return new RspTemplate<>(HttpStatus.OK, "단어 단건 조회", wordInfoResDto);
    }

}
