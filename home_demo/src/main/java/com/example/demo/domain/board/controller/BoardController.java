package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public void boardRegister (@RequestBody BoardRequest boardRequest) {
        log.info("boardRegister()");//로깅

        boardService.register(boardRequest);//자바객체를 보드서비스의 register메서드로 전달
    }
}
