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
    private final MemberRepository memberRepository;
    ////SpringDataJpa가 구현체를 만들어서 등록을해줌
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository= memberRepository;
    }


//    @PersistenceContext //생성자안만들고 이렇게해도된대
    private EntityManager em;
    private DataSource dataSource;


    /*스프링부트가 설정을보고 데이터소스를 만들어줌 얘를 주입을함*/
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    스프링이뜰때 컨피규레이션읽고 스프링빈에 등록하라는뜻으로 인식

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em= em;
//    }

    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);//스프링dataJPA용
    }
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository()
//        return new JdbcMemberRepository(dataSource);
//            return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
        //다른저장소로 변경할때 이것만
        //return new dbMemberRepository(); 이렇게 바꿔주면 다른코들르 손볼 필요도없이 바꿔치기가능
    }

//컴포넌트 스캔대신에 이렇게 쓰면 된다는데 이렇게쓰면 에러난다. 왜그럴까.?
//    @Bean //등록되서 쓴다는걸 알수있게 이렇게함
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
