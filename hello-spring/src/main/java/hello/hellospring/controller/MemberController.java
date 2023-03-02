package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
/*
* 스프링컨테이너라는 통이생기는데 이 어노테이션이있으면 멤버컨트롤러객체를 생성해서 스프링에 넣어두고 관리함.
* 이거를 스프링컨테이너에서 스프링빈이 관리된다는것.*/
public class MemberController {
    //멤버서비스를 가져다가 써야하는데,
    //스프링컨테이너에서 받아써야하는데
    //MemberService는 하나만생성해서 공유해서 쓰는게 낫다 (스프링컨테이너에 등록해서
    private final MemberService memberService; //  = new MemberService();이렇게 하지말것
    @Autowired
    //멤버컨트롤러가 생성될때 스프링빈에 등록되어있는 객체를 가져다가 넣어줌.이게바로 의존관계주입.
    //스프링컨테이너의 멤버서비스를 가져다가 연결을 시켜줌
    //helloController는 스프링이뜰때 스프링컨테이너에 등록되는데,
    //멤버서비스는 순수한 자바클래스라서 스프링이 얘를 알수가없다. 그래서 서비스에@Service추가해야함.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService= "+memberService.getClass());
        //aop적용되면, 멤버서비스를 가지고 복제해서 코드를 조작하는기술.(프록시)
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form){
        //멤버폼에 멤버변수에 name(키값)으로  인풋태그의 name="키값"이 들어옴
        //세터로 값을 넣어주고 우리는 게터로 값을 꺼내면된다.
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/"; //끝나면 홈화면으로 이동
    }

    @GetMapping("/members")
    public String list(Model model) {
        //모델에 담아서 화면으로넘김
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
