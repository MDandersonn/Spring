package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;
    /*스프링부트가 설정을보고 데이터소스를 만들어줌 얘를 주입을함*/
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    스프링이뜰때 컨피규레이션읽고 스프링빈에 등록하라는뜻으로 인식
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository()
        return new JdbcMemberRepository(dataSource);
        //다른저장소로 변경할때 이것만 
        //return new dbMemberRepository(); 이렇게 바꿔주면 다른코들르 손볼 필요도없이 바꿔치기가능
    }
}
