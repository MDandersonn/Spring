package com.mysite.sbb;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    
    @ManyToOne
    //답변은 하나의 질문에 여러개가 달릴 수 있는 구조이다. 
    private Question question;
    //질문객체가 담긴 칼럼 (어떤 질문의 답변인지 알아야하므로 질문 속성이 필요하다)
    //답변 객체(예:answer)를 통해 질문 객체의 제목을 알고 싶다면 answer.getQuestion().getSubject()처럼 접근할 수 있다. 
}