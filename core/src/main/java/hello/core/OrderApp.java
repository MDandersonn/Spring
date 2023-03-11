package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//의존성주입 쓰면서 주석처리
        //MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
        
        //스프링쓰면서 주석처리
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService= appConfig.memberService();
//        OrderService orderService = appConfig.orderService();


        //스프링설정   ApplicationContext :모든 객체들을 관리해줌(BEAN들)
// 앱config에있는 환경설정정보를 가지고 스프링이 bean들을 스프링컨테이너에 등록해서 관리해줌
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class);
        OrderService orderService=applicationContext.getBean("orderService",OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
