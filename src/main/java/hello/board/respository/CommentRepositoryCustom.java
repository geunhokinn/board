package hello.board.respository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.board.model.entity.Comment;
import hello.board.model.entity.QBoard;
import hello.board.model.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static hello.board.model.entity.QBoard.board;
import static hello.board.model.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Comment find(Long commentNo, Long boardNo) {
        return queryFactory.select(comment)
                .from(comment)
                .where(comment.commentNo.eq(commentNo))
                .where(board.boardNo.eq(boardNo))
                .fetchOne();
    }
}
