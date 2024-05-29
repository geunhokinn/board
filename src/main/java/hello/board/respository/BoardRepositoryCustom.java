package hello.board.respository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.board.model.entity.Board;
import hello.board.model.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hello.board.model.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<Board> find(Long boardNo) {
        return Optional.ofNullable(
                queryFactory.select(board)
                        .from(board)
                        .leftJoin(board.comments) // inner join 하면 댓글이 없는 게시글은 안 보임
                        .fetchJoin() // 페치 조인으로 n+1 문제 해결
                        .where(board.boardNo.eq(boardNo))
                        .fetchOne()
        );
    }
}
