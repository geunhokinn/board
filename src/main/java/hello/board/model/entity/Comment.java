package hello.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "comment_status='ACTIVE") // 삭제되지 않은 것만 조회
@SQLDelete(sql = "UPDATE comment SET comment_status='DELETE' WHERE comment_no=?") // soft delete
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo; // 댓글 번호

    private String body; // 댓글 본문

    @Enumerated(EnumType.STRING) // enum 타입 DB 에 String 으로 저장
    private CommentStatus commentStatus; // 댓글 상태 값(soft delete 를 하기 때문에 상태 값으로 관리)

    @ManyToOne
//    @JoinColumn 생략
    private Board board;
}
