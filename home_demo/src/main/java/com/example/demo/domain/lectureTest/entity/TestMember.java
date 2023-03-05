package com.example.demo.domain.lectureTest.entity;

import lombok.Data;

@Data//게터세터생성자투스트링 다 생성
public class TestMember {

    final private Long memberId;
    final private String name;
    final private Long age;
}
