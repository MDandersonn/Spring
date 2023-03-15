package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
			//리포지터리의 대상이 되는 엔티티의 타입(Question)과 해당 엔티티의 PK의 속성 타입(Integer)을 지정
	 	Question findBySubject(String subject);
	 	Question findBySubjectAndContent(String subject, String content);
	    List<Question> findBySubjectLike(String subject);
}
