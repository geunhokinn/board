package hello.board.model.response;

import hello.board.model.entity.Board;
import hello.board.model.entity.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder // 빌더 패턴
public class BoardWriteResponse {

    private Long boardNo;
    private String title;
    private String body;
    private BoardStatus boardStatus;
    // 댓글 이따가 추가

    static public BoardWriteResponse from(Board board) {
        return new BoardWriteResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getBody(),
                board.getBoardStatus()
        );
    }
}
