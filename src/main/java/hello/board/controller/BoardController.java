package hello.board.controller;

import hello.board.model.request.BoardDeleteRequest;
import hello.board.model.request.BoardEditRequest;
import hello.board.model.request.BoardWriteRequest;
import hello.board.model.response.BoardResponse;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 조회(게시판 리스트 조회)
    @GetMapping("boardList") // 페이징 기법 사용
    public List<BoardResponse> searchBoardList(
            @RequestParam int page, // 페이지 번호
            @RequestParam int pageSize, // 페이지의 데이터 개수
            @RequestParam Sort.Direction direction) { // 정렬 기준
        return  boardService.searchBoardList(page, pageSize, direction);
    }

    // 게시글 조회(단일 게시글 조회)
    @GetMapping("board")
    public BoardResponse searchBoard(@RequestParam Long boardNo) {
        return boardService.getBoard(boardNo);
    }

    // 게시글 작성
    @PostMapping("board")
    public BoardResponse writeBoard(@RequestBody BoardWriteRequest boardWriteRequest) {
        return boardService.writeBoard(boardWriteRequest.getTitle(), boardWriteRequest.getBody());
    }

    // 게시글 수정
    @PutMapping("board")
    public BoardResponse editBoard(@RequestBody BoardEditRequest boardEditRequest) {
        return boardService.editBoard(boardEditRequest.getBoardNo(), boardEditRequest.getBody());
    }

    // 게시글 삭제
    @DeleteMapping("board")
    public Long deleteBoard(@RequestBody BoardDeleteRequest boardDeleteRequest) {
        return boardService.deleteBoard(boardDeleteRequest.getBoardNo());
    }

}
