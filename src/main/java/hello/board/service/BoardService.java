package hello.board.service;

import hello.board.model.entity.Board;
import hello.board.model.entity.BoardStatus;
import hello.board.model.response.BoardWriteResponse;
import hello.board.respository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardWriteResponse writeBoard(String title, String body) {
        Board board = new Board();
        board.setTitle(title);
        board.setBody(body);
        board.setBoardStatus(BoardStatus.ACTIVE);
        return BoardWriteResponse.from(boardRepository.save(board));
    }
}
