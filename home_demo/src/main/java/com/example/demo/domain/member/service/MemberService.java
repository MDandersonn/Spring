package com.example.demo.domain.member.service;
import com.example.demo.domain.member.service.request.MemberLoginRequest;
import com.example.demo.domain.member.service.request.MemberRegisterRequest;
import com.example.demo.domain.member.service.request.EmailMatchRequest;
import com.example.demo.domain.member.service.request.EmailPasswordRequest;
public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest memberRegisterRequest);
    String signIn(MemberLoginRequest memberLoginRequest);
    //void logout(String userToken);//제거

    Boolean applyNewPassword(EmailPasswordRequest toEmailPasswordRequest);

    Boolean emailMatch(EmailMatchRequest toEmailMatchRequest);

}