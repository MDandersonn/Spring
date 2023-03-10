package com.example.demo;
import com.example.demo.domain.board.controller.request.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.domain.board.service.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void boardTest() {

		Optional<Board> maybeBoard = boardRepository.findById(1L);
		Board board = maybeBoard.get();
//아이디 1번의 모든 컬럼들이 담긴 board
		BoardRequest boardRequest = new BoardRequest(
				board.getTitle(), board.getWriter(), board.getContent());
//아이디 1번의 작성자를 검색해서 찾아냄  작성자는 김영진임
		List<Board> conditionMatchingBoardList =
				boardRepository.findByBoardIdAndWriter(1L, boardRequest.getWriter());
		//아이디 1번,김영진으로 검색해서 모든칼럼들을 출력

		System.out.println("result: " + conditionMatchingBoardList.get(0)); //리스트의 0번요소를 출력함.
	}
}