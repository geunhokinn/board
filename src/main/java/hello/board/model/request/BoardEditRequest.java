package hello.board.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardEditRequest {
    // 게시글 수정은 본문만 가능함
    private Long boardNo;
    private String body;
}
