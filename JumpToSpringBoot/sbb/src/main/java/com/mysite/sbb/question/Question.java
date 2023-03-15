package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
//안전하지못해서 비추

@Entity
public class Question {
    @Id // 이 속성을 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)//1씩 고유번호 증가.
    /*
 strategy 옵션을 생략할 경우에 @GeneratedValue 애너테이션이 지정된 컬럼들이 모두 동일한 시퀀스로 번호를 생성하기 때문에 일정한 순서의 고유번호를 가질수 없게 된다. 
 이러한 이유로 보통 GenerationType.IDENTITY를 많이 사용한다.
     */
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    //columnDefinition = "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용한다.
    //엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블 컬럼으로 인식한다.
    //테이블 컬럼으로 인식하고 싶지 않은 경우에만 @Transient 애너테이션을 사용한다.
    private String content;

    // createDate 속성의 실제 테이블의 컬럼명은 create_date가 된다
    @CreationTimestamp
    private LocalDateTime createDate;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    //질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서
    private List<Answer> answerList;
    //이제 질문 객체(예:question)에서 답변을 참조하려면 question.getAnswerList()를 호출
}