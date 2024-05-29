package hello.board.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDeleteRequest {
    // 게시글 삭제는 게시글 번호만 있으면 됨
    private Long boardNo;
}
