package hello.board.respository;

import hello.board.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query(value = "UPDATE comment SET comment_status='DELETE' where comment_no IN (:commentNos)", nativeQuery = true)
    void deleteCommentList(@Param("commentNos") List<Long> commentNos);
}
