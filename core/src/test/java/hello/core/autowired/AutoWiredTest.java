package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false)//기본값은 true임  true로 설정하면 이러면 예외터짐
        public void setNoBean1(Member member) { //Member는 스프링이 관리하는게 아님 -> 주입못함
            System.out.println("setNoBean1 = " + member);
        } //null 호출

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }//Optional.empty 호출


        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }

    }
}
