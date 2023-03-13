package com.example.demo.forTest.boardComment;

import com.example.demo.domain.forTest.board.controller.response.CommentResponse;
import com.example.demo.domain.forTest.board.entity.Comment;
import com.example.demo.domain.forTest.board.entity.TestBoard;
import com.example.demo.domain.forTest.board.repository.CommentRepository;
import com.example.demo.domain.forTest.board.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardCommentTest {
    //@Component안써도 스프링빈에등록해줌 스프링부트라서? JPA라서?
    @Autowired
    private TestBoardRepository testBoardRepository;
    @Autowired
    private CommentRepository commentRepository;
    //final로 한 다음 생성자를 만든다면 제약사항이 걸리는데
    //그렇게하면 테스트할때 제약이걸려서 그렇게까지할필요가없음

    @Test
    public void 게시물_저장() {
//      TestBoard board = new TestBoard("제목", "내용");
        TestBoard board = new TestBoard("테스트", "뭐");
        testBoardRepository.save(board);
    }

    @Test
    public void 덧글_저장() {
//      Optional<TestBoard> mayTestBoard = testBoardRepository.findById(1L);
//      Optional<TestBoard> mayTestBoard = testBoardRepository.findById(2L);
        Optional<TestBoard> mayTestBoard = testBoardRepository.findById(4L);
        TestBoard testBoard = mayTestBoard.get();

//      Comment comment = new Comment("덧글");
        Comment comment = new Comment("시작3");
        testBoard.setComment(comment);

        testBoardRepository.save(testBoard);
        commentRepository.save(comment);
    }

    @Test
    public void 게시물_덧글_출력() {
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(2L);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponses.add(new CommentResponse(comment.getContent()));
//            System.out.println(comment);
        }
        System.out.println(commentResponses);
    }

    @Test
    public void 덧글_수정() {
        Optional<Comment> maybeComment = commentRepository.findById(4L);
        Comment comment = maybeComment.get();

        comment.changeContent("이러쿵 저러쿵 요러쿵");
        commentRepository.save(comment);
    }

    @Test
    public void 덧글_삭제() {
        commentRepository.deleteById(5L);
    }

    @Test
    public void 게시글_삭제() {
        final Long boardId = 2L;
        List<Comment> commentList = commentRepository.findAllCommentsByBoardId(boardId);

        for (Comment comment : commentList) {
            System.out.println("comment 내용: " + comment.getContent());
            commentRepository.delete(comment);
        }// 딸린자식먼저 다 지우고 지워야함
        testBoardRepository.deleteById(boardId);
    }
}