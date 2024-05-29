package hello.board.service;

import hello.board.model.entity.Board;
import hello.board.model.entity.BoardStatus;
import hello.board.model.response.BoardResponse;
import hello.board.respository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse writeBoard(String title, String body) {
        Board board = new Board();
        board.setTitle(title);
        board.setBody(body);
        board.setBoardStatus(BoardStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }

    public BoardResponse editBoard(Long boardNo, String body) {
        return boardRepository.findById(boardNo)
                .map(board -> {
                    board.setBody(body);
                    return board;
                }).map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }
}
