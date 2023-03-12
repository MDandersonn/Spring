package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//설정정보 //싱글톤패턴유지해줌(하위클래스만들어서)
//이게없으면 스프링컨테이너에서 빈을 관리하지 못함.
public class AppConfig {//객체의 생성과 연결 담당.
//    public MemberService memberService () {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//
//    }

    //아래와 같이  명확하게 역할이 보이게 리팩토링한다.

    //@Bean memberService 호출-> new MemoryMemberRepository()생성
    //@Bean orderService 호출-> new MemoryMemberRepository()생성
    //두번 호출되서 두번 생성 됨 ,  이러면 싱글톤이 깨지나?

    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("CALL AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean//스프링컨테이너에 등록을해줌
    public MemberRepository memberRepository(){
        System.out.println("CALL AppConfig.memberRepository");
        return new MemoryMemberRepository();//어떤 구현체 레포지토리를 넣어줄지선택
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();//어떤 구현체 레포지토리(할인정책)를 넣어줄지선택
    }
/*
    싱글톤패턴의 유지:
    @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면
    생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
 */

}
