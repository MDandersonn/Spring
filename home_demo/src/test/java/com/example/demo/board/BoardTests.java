package com.example.demo.board;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class BoardTests {
    @Autowired
    private BoardService boardService;
    @Test
    public void 게시물_저장_테스트(){
        BoardRequest boardRequest = new BoardRequest("이거","정말","되나");
//        System.out.println(boardService.register(boardRequest));
        boardService.register(boardRequest);
    }
    @Test
    public void 게시물_저장_리스트_테스트(){
        System.out.println(boardService.list());
    }
    @Test
    public void 게시물_수정_테스트(){
        Board board =boardService.modify(1L,new BoardRequest("뭐야","왜","변경하니"));
        System.out.println(board);
    }
    @Test
    public void 게시물_삭제_테스트(){
        boardService.remove(1L);
        boardService.read(1L);
    }
    @Test
    public void 현재_게시물의_개수 () {
        System.out.println(boardService.getCount());
    }
    @Test
    public void 마지막_엔티티_id번호 () {
        System.out.println(boardService.getLastEntityId());
    }

    @Test
    public void 게시판_구동_전체_테스트 () {
        BoardRequest boardRequest =
                new BoardRequest("이거", "정말", "되냐");
        Board board=boardService.register(boardRequest);
//      Long lastBoardId = boardService.getLastEntityId();//id중 최대값을 가져와서 적용시킨다.
        Long lastBoardId = board.getBoardId();// 보드ID를 가져와서 적용시킨다.
        System.out.println("초기 등록: " + boardService.read(lastBoardId));

        boardService.modify(lastBoardId, new BoardRequest(
                "뭐야", "왜", "변경하니 ?"));

        System.out.println("수정 후: " + boardService.read(lastBoardId));

        boardService.remove(lastBoardId);

        System.out.println("삭제 후: " + boardService.read(lastBoardId));
    }


}
