package hello.board.service;

import hello.board.model.response.BoardResponse;
import hello.board.respository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final BoardRepository boardRepository;

    public BoardResponse postComment(Long boardId, String commentBody) {
        return boardRepository.findById(boardId)
                .map(board -> {
                    board.addComment(commentBody); // 게시글에 댓글 추가
                    return board;
                })
                .map(boardRepository::save) // 게시글 + 댓글 저장
                .map(BoardResponse::from) // 응답 객체로 변환
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }
}
