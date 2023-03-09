package com.example.demo.domain.vueTest.controller.request;

import lombok.Getter;
import lombok.ToString;
/*
* Lombok(롬복)은 Java 라이브러리로 반복되는 getter, setter, toString 등의 메서드 작성 코드를 줄여주는 코드 다이어트 라이브러리
*
* @Getter
@Setter
@ToString
@NoArgsConstructor  //매개변수없는생성자
@AllArgsConstructor // 모든멤버변수포함한 생성자
* 이 모든걸 합쳐서 @Data 라고 축약가능
* */
@ToString
@Getter
public class VueRequestTestData {

    private String memberName;
    private String major;
}
