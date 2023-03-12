package hello.core;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;

/*
컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다. 그래서
excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트
스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.

컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로등록한다. @Component 를 붙여주자.

 */

@Configuration            //이 애노테이션 붙은애는 뺼것이라는말 Configuration어노테이션이 붙은 클래스는 빼고 스캔
@ComponentScan(
        //basePackages ="hello.core.member",//이위치에서부터 하위패키지로 찾아들어가라는의미
        //basePackageClasses = AutoAppConfig.class,//지정한 클래스의 패키지인 hello.core부터 찾음
        //만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
        // 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

public class AutoAppConfig {
//    이렇게 여기에 @BEAN으로 안하고 각 클래스에 @Component 로 처리하면
//    의존관계주입은 어떻게 할까?  ->자동의존관계주입을한다 @Autowired
    //즉 @Component 와 @Autowired는 같이 쓰임.


    
    /*
    //만약 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌되면 어떻게 될까?
    //-> 수동 빈이 우선이다.
    //최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 했음

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}