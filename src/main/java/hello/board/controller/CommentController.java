package hello.board.controller;

import hello.board.model.request.CommentDeleteRequest;
import hello.board.model.request.CommentEditRequest;
import hello.board.model.request.CommentPostRequest;
import hello.board.model.response.BoardResponse;
import hello.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("comment")
    public BoardResponse post(@RequestBody CommentPostRequest commentPostRequest) {
        return commentService.postComment(commentPostRequest.getBoardNo(), commentPostRequest.getCommentBody());
    }

    @PutMapping("comment")
    public String edit(@RequestBody CommentEditRequest commentEditRequest) {
        commentService.editComment(commentEditRequest.getBoardNo(), commentEditRequest.getCommentNo(), commentEditRequest.getCommentBody());

        return "OK";
    }

    @DeleteMapping("comment")
    public String delete(@RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.deleteComment(commentDeleteRequest.getBoardNo(), commentDeleteRequest.getCommentNo());

        return "OK";
    }
}
