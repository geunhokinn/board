package hello.board.service;

import hello.board.model.entity.Comment;
import hello.board.model.response.BoardResponse;
import hello.board.respository.BoardRepository;
import hello.board.respository.BoardRepositoryCustom;
import hello.board.respository.CommentRepository;
import hello.board.respository.CommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private final CommentRepository commentRepository;

    public BoardResponse postComment(Long boardId, String commentBody) {
        return boardRepositoryCustom.find(boardId)
                .map(board -> {
                    board.addComment(commentBody); // 게시글에 댓글 추가
                    return board;
                })
                .map(boardRepository::save) // 게시글 + 댓글 저장
                .map(BoardResponse::from) // 응답 객체로 변환
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    public void editComment(Long boardNo, Long commentNo, String commentBody) {
        Comment comment = commentRepositoryCustom.find(commentNo, boardNo);
        if (comment == null) throw new RuntimeException("존재하지 않는 게시글입니다.");
        comment.setBody(commentBody);
    }

    public void deleteComment(Long boardNo, Long commentNo) {
        Comment comment = commentRepositoryCustom.find(commentNo, boardNo);
        if (comment == null) throw new RuntimeException("존재하지 않는 게시글입니다.");
        commentRepository.delete(comment);
    }
}
