package hello.board.model.response;

import hello.board.model.entity.Board;
import hello.board.model.entity.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder // 빌더 패턴
public class BoardResponse {

    private Long boardNo;
    private String title;
    private String body;
    private BoardStatus boardStatus;
    private List<CommentResponse> comments; // 댓글

    static public BoardResponse from(Board board) {
        return new BoardResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getBody(),
                board.getBoardStatus(),
                board.getComments() // 댓글 리스트 // n+1 문제를 해결해서 다시 댓글을 조회하지 않음
                        .stream() // 댓글을 스트림으로 변환
                        .map(CommentResponse::from) // 댓글을 응답 객체로 변환
                        .collect(Collectors.toList()) // 스트림을 리스트로 수집
        );
    }
}
