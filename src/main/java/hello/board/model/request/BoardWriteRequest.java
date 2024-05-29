package hello.board.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardWriteRequest {
    // 제목과 본문이 있는 요청 객체
    private String title;
    private String body;
}
