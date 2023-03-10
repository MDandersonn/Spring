package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SbbApplicationTests {

	// 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성해 준다.
	// 스프링이 객체를 대신 생성하여 주입한다.
	/*
	 * 순환참조 문제와 같은 이유로 Autowired 보다는 생성자를 통한 객체 주입방식이 권장된다. 하지만 테스트 코드의 경우에는 생성자를 통한
	 * 객체의 주입이 불가능하므로 테스트 코드 작성시에만 Autowired를 사용하고 실제 코드 작성시에는 생성자를 통한 객체 주입방식을
	 * 사용하겠다.
	 */
	@Autowired
	private QuestionRepository questionRepository;

	/*
	 * testJpa 메서드 위의 @Test 애너테이션은 testJpa 메서드가 테스트 메서드임을 나타낸다. 위 클래스를 JUnit으로
	 * 실행하면 @Test 애너테이션이 붙은 메서드가 실행된다. JUnit은 테스트코드를 작성하고 작성한 테스트코드를 실행하기 위해 사용하는
	 * 자바의 테스트 프레임워크이다.
	 */
	@Test
	@Order(1)
	void 통합테스트() {
//저장 
		Question q1 = new Question();
//		q1.setId(1);
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
//	db에 저장할때 q1을 쓰는게아니라 q1에서 필요한값만 빼와서 저장시키고 id는 q1에서 가져오는게아니라 자동생성으로 가져와서
//db에 저장한다. 그러니까 q1의 아이디랑 저장된 아이디가 다르지.
// q1객체가생성될때 생성된id를 넣어줌 ,그후 save메서드가오면  q1에서 id를 꺼내는게아니라 그 생성된id를 db에 따로넣어줌
		this.questionRepository.save(q1); // 첫번째 질문 저장

		Question q2 = new Question();
//		q2.setId(2);
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // 두번째 질문 저장

//모든 리스트 출력.
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q3 = all.get(0);
		assertEquals("sbb가 무엇인가요?", q3.getSubject());
		System.out.println("q3의 아이디: "+ q3.getId());

//한 객체 출력
		Optional<Question> oq = this.questionRepository.findById(1);// 1번ID인거 출력
		if (oq.isPresent()) {// oq가 null이 아니라면( 아이디에 해당하는 row가 존재한다면)
			Question q4 = oq.get();// 그 질문객체를 가져와서
			assertEquals("sbb가 무엇인가요?", q4.getSubject());// 그 질문객체의 Subject를 출력
			System.out.println("q1의 아이디: "+ q1.getId());
			System.out.println("q4의 아이디: "+ q4.getId());
		}

//한개객체출력투
		Question q5 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		System.out.println("q1의 아이디: "+ q1.getId());
		System.out.println("q5의 아이디: "+ q5.getId());
		assertEquals(q1.getId(), q5.getId());

		Question q6 = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		System.out.println("q1의 아이디: "+ q1.getId());
		System.out.println("q6의 아이디: "+ q6.getId());
		assertEquals(q1.getId(), q6.getId());
	}
}
