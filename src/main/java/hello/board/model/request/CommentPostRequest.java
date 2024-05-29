package hello.board.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentPostRequest {
    private Long boardNo; // 게시글 번호
    private String commentBody; // 댓글 본문
}
