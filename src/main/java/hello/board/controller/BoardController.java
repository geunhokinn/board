package hello.board.controller;

import hello.board.model.request.BoardWriteRequest;
import hello.board.model.response.BoardWriteResponse;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("board")
    public BoardWriteResponse writeBoard(@RequestBody BoardWriteRequest boardWriteRequest) {
        return boardService.writeBoard(boardWriteRequest.getTitle(), boardWriteRequest.getBody());
    }
}
