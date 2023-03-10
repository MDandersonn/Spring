package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
/*@Controller와는 다르게 @RestController는 리턴값에 자동으로 @ResponseBody가 붙게되어
별도 어노테이션을 명시해주지 않아도 HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달 된다.
//원래는 해당 정적페이지로 이동하는데, 자바객체로보내주게하는것.
* */

/*
@RequestBody / @ResponseBody 정리.
클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로 보내면,
서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.
서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로 변환하여
클라이언트로 전송한다.
*/
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
