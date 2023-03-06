package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class BoardController {

    final private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/register")//http://localhost:7777/board/register로 데이터 보내온거 받아옴
    public void boardRegister (@RequestBody BoardRequest boardRequest) {//UI에서 요청온것을 자바객체로 바꿔줌
        log.info("boardRegister()");//로깅

        boardService.register(boardRequest);//자바객체를 보드서비스의 register메서드로 전달
    }
    @GetMapping("/list")
    public List<Board> boardList () {
        log.info("boardList()");

        return boardService.list();//이걸 다시 Vue.js로 반환함. then()안의 res로 전달
    }

    @GetMapping("/{boardId}")//가변인자면 이렇게받나?
    public Board boardRead(@PathVariable("boardId") Long boardId) {
        log.info("boardRead()");

        return boardService.read(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void boardRemove(@PathVariable("boardId") Long boardId) {
        log.info("boardRemove()");

        boardService.remove(boardId);
    }

    @PutMapping("/{boardId}")
    public Board boardModify(@PathVariable("boardId") Long boardId, @RequestBody BoardRequest boardRequest) {
//{ title, writer, content },  //@RequestBody: UI에서 요청온것을 자바객체로 바꿔줌
        log.info("boardModify(): " + boardRequest + "id: " + boardId);
        return boardService.modify(boardId, boardRequest);
    }
}
