package com.example.demo.domain.member.controller;
import com.example.demo.domain.member.controller.form.MemberLoginForm;
import com.example.demo.domain.member.controller.form.MemberRegisterForm;
import com.example.demo.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.member.controller.form.EmailMatchForm;
import com.example.demo.domain.member.controller.form.EmailPasswordForm;
import com.example.demo.domain.security.service.RedisService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class MemberController {

    final private MemberService memberService;
    final private RedisService redisService;

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

    @PostMapping("/emailMatch")
    public Boolean emailMatchPhone(@Validated @RequestBody EmailMatchForm form, BindingResult bindingResult) {
        log.info("MainFormController#emailMatchPhone: {}", form);
        if (bindingResult.hasFieldErrors()) {
            return false;
        }
        return memberService.emailMatch(form.toEmailMatchRequest());
    }

    @PostMapping("/applyNewPassword")
    public Boolean applyNewPassword(@Validated @RequestBody EmailPasswordForm form) {
        log.info("MainFormController#applyNewPassword: {}", form);

        return memberService.applyNewPassword(form.toEmailPasswordRequest());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);//=이 붙어서 제거해줌.
        log.info("logout(): " + token);

        redisService.deleteByKey(token);
    }

}