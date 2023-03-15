package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	//@NotEmpty는 해당 값이 Null 또는 빈 문자열("")을 허용하지 않음을 의미한다.
    //그리고 여기에 사용된 message 속성은 검증이 실패할 경우 화면에 표시할 오류 메시지이다
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}