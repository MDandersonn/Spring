package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository; //SpringDataJPA 사용시 필요
    //SpringDataJpa가 구현체를 만들어서 등록을해줌
    @Autowired
    public SpringConfig(MemberRepository memberRepository) { //SpringDataJPA 사용시 필요
        this.memberRepository= memberRepository;
    }


//    @PersistenceContext //생성자안만들고 이렇게해도된대 ??????????


//    private DataSource dataSource;           //Jdbc ,JdbcTemplate사용할때 필요
    /*스프링부트가 설정을보고 데이터소스를 만들어줌 얘를 주입을함
    * DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션
    정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다*/

//    @Autowired
//    public SpringConfig(DataSource dataSource) {       //Jdbc ,JdbcTemplate사용할때 필요
//        this.dataSource = dataSource;
//    }
//    스프링이뜰때 컨피규레이션읽고 스프링빈에 등록하라는뜻으로 인식



//    private EntityManager em;//JPA쓸때 필요
//    @Autowired
//    public SpringConfig(EntityManager em) {//JPA쓸때 필요
//        this.em= em;
//    }

    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());//memory,Jdbc,JdbcTemplate,JPA 쓸때
        return new MemberService(memberRepository);//스프링dataJPA
        //return하면 해당타입의 객체가 스프링IOC 컨테이너 안에 빈으로 등록됨.
        //서로간의 의존성주입을 IOC가 해줌. 즉  생성자에 매개변수는 스프링IOC컨테이너가 빈을 찾아서 넣어주는것
        //의존성주입은 빈 끼리만 가능 . 즉 스프링IOC컨테이너안에 있는 빈끼리만 의존성주입가능


    }
//    @Bean
//    public MemberRepository memberRepository(){//SpringDataJPA사용시 이 메서드가 필요가없다.

//        return new MemoryMemberRepository()//Memory용
//        return new JdbcMemberRepository(dataSource);//Jdbc용
//        return new JdbcTemplateMemberRepository(dataSource);//JdbcTemplate용
//        return new JpaMemberRepository(em);//JPA용
        //다른저장소로 변경할때 이것만
        //return new dbMemberRepository(); 이렇게 바꿔주면 다른코들르 손볼 필요도없이 바꿔치기가능
//    }

//컴포넌트 스캔대신에 이렇게 쓰면 된다는데 이렇게쓰면 에러난다. 왜그럴까.?
//    @Bean //등록되서 쓴다는걸 알수있게 이렇게함
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
