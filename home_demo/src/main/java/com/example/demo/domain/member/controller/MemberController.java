package com.example.demo.domain.member.controller;
import com.example.demo.domain.member.controller.form.MemberLoginForm;
import com.example.demo.domain.member.controller.form.MemberRegisterForm;
import com.example.demo.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class MemberController {

    final private MemberService memberService;

    @PostMapping("/check-email/{email}")//이메일체크
    public Boolean emailValidation(@PathVariable("email") String email) {
        log.info("emailValidation(): " + email);

        return memberService.emailValidation(email);
    }
    @PostMapping("/sign-up")//회원가입
    public Boolean signUp(@RequestBody MemberRegisterForm form) {
        log.info("signUp(): " + form);

        return memberService.signUp(form.toMemberRegisterRequest());
    }
    
    @PostMapping("/sign-in")//로그인
    public String signIn(@RequestBody MemberLoginForm form) {
        log.info("signIn(): " + form);

        return memberService.signIn(form.toMemberLoginRequest());
    }

}