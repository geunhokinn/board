package hello.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "board_status='ACTIVE'") // 삭제되지 않은 것만 조회
@SQLDelete(sql = "UPDATE board SET board_status='DELETE' WHERE board_no=?") // soft delete
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long boardNo; // 게시판 번호

    private String title; // 게시판 제목
    private String body; // 게시판 본문

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus; // 게시판 상태 값(soft delete 를 하기 때문에 상태 값으로 관리)

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // 지연 로딩 -> 게시글 조회 시 로딩이 안됨
    private List<Comment> comments = new ArrayList<>(); // 게시글에는 여러 개의 댓글이 있음

    public Board addComment(String commentBody) {
        Comment comment = new Comment();
        comment.setBody(commentBody);
        comment.setBoard(this); // 댓글이 게시글을 가지고 있음
        comment.setCommentStatus(CommentStatus.ACTIVE);
        this.comments.add(comment); // 게시글도 댓글을 가지고 있음
        return this;
    }
}
