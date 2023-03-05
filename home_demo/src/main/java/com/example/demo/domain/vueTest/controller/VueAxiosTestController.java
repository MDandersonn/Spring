package com.example.demo.domain.vueTest.controller;

import com.example.demo.domain.vueTest.controller.request.VueRequestTestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j//로깅을 위함
/*
* */
@RestController
@RequestMapping("/vue/first")
//@RequestMapping은 method를 생략하면 GET 방식과 POST 방식을 모두 처리해준다.
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
//웹 페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 메커니즘
//특정 도메인만 접속을 허용할 수도 있다.

public class VueAxiosTestController {

    @PostMapping("/receive-test")
    public void receiveTest (@RequestBody VueRequestTestData vueRequestTestData) {
        //@RequestBody:  HTTP요청 바디를 자바객체로 변환 이때 HttpMessageConverter를 사용
        //HTTP 요청의 바디내용을 통째로 자바객체로 변환해서 매핑된 메소드 파라미터로 전달해준다


//        롬복에서는  @Slf4j같은 Logger 어노테이션을 붙여주면 log라는 변수로 로깅을 사용할 수 있습니다.
        log.info("요청된 데이터 정보 확인: " + vueRequestTestData);
    }
}
