package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //태스트끝날때마다 리포지토리를 깔끔하게 지워줌  메서드끝날때마다 새로
    @AfterEach
    public void afterEach(){
        repository.clearStroe();
        //테스트가 실행되고 끝날떄마다 저장소를 지움.
        //테스트는 서로 의존관계없이 독립적으로 실행되야함.

    }
    @Test//junit불러오기
    public void save(){
        Member member = new Member();
        member.setName("String");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();//옵셔널에서 값을 꺼낼때는 .get() 으로 꺼냄 (좋은방법은아님)
        System.out.println("result= " +(result == member));//이과정을 어써션스기능으로 대체

        Assertions.assertEquals(member,result);//다르면 빨간불뜸

        assertThat(member).isEqualTo(result); //얘는 멤버가 result랑 똑같

    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("Spring1");
        repository.save(member1);
        System.out.println("findByname member1: "+member1);

        //쉬프트 f6하면 같은것모아서 수정가능
        Member member2=new Member();
        member2.setName("Spring2");
        repository.save(member2);

        System.out.println("findByname member2: "+member2);

        Member result=repository.findByName("Spring1").get();//먼저찾으면 종료하게되어있는데. findall이 먼저실행되면 findall에서 먼저만들어져서
        //findall의 객체를 반환함
        System.out.println("result: "+result);
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        System.out.println("findall member1: "+member1);
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        System.out.println(" findall member2: "+member2);
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
