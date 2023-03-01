package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")//static폴더의 index.html보다 컨트롤러 맵핑이 더 우선순위.
    public String home(){
        return "home";
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberFrom";
    }

}
