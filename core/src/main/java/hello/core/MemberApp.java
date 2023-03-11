package hello.core;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //의존성주입을 사용하면서 주석처리.
        //MemberService memberService = new MemberServiceImpl();

        //스프링을 사용하면서 주석처리
//        AppConfig appConfig =new AppConfig();
//        MemberService memberService= appConfig.memberService();

//스프링설정   ApplicationContext :모든 객체들을 관리해줌(BEAN들)
// 앱config에있는 환경설정정보를 가지고 스프링이 bean들을 스프링컨테이너에 등록해서 관리해줌
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AppConfig.class);

        //config에 등록된 메서드이름memberService 을 name에적음,   타입)
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);//컨트롤 알트 v
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
