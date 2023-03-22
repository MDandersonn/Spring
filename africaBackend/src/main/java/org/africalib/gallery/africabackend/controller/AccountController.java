package org.africalib.gallery.africabackend.controller;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import io.jsonwebtoken.Claims;
import org.africalib.gallery.africabackend.entity.Item;
import org.africalib.gallery.africabackend.entity.Member;
import org.africalib.gallery.africabackend.repository.ItemRepository;
import org.africalib.gallery.africabackend.repository.MemberRepository;
import org.africalib.gallery.africabackend.service.JwtService;
import org.africalib.gallery.africabackend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")//인자값을 params로 받고
    public ResponseEntity login(@RequestBody Map<String,String> params,
                                HttpServletResponse res ){
        Member member = memberRepository.findByEmailAndPassword(params.get("email"),params.get("password"));
        if(member !=null){

            int id= member.getId();
            String token=jwtService.getToken("id",id);

            //보안상 클라이언트보단, 서버에 쿠키값저장하는게 낫다.
            //토큰을 쿠키에넣고 응답값에 추가를하고 토큰
            Cookie cookie = new Cookie("token",token);
            cookie.setHttpOnly(true);//자바스크립트로는 접근못하게
            cookie.setPath("/");

            res.addCookie(cookie);//응답값에 추가
//            return ResponseEntity.ok().build();//응답값이 비어있음 res.data =''
            return new ResponseEntity<>(id,HttpStatus.OK);//응답값을 id값을줌
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value= "token", required=false) String token){
        Claims claims= jwtService.getClaims(token);
        if(claims !=null){
            int id = Integer.parseInt( claims.get("id").toString() );
            return new ResponseEntity<>(id,HttpStatus.OK);//응답값을 id값을줌
        }
        return new ResponseEntity<>(null,HttpStatus.OK);//null일때
        //토큰을넣으면 아이디를반환

    }
    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
