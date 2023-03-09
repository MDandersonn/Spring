package com.example.demo.domain.board.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// @RequiredArgsConstructor어노테이션은 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션입니다.
@RequiredArgsConstructor
public class BoardRequest {
//    private String title;
//    private String writer;
//    private String content;
    final private String title;
    final private String writer;
    final private String content;
}
