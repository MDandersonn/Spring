package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링컨테이너와 테스트를 함께 실행해줌
@Transactional // 테스트를 실행할때 트랜잭션실행하고 db에 데이터를 인서트쿼리하고 테스트를 끝나면 롤백을함
    //db넣었던 데이터 반영이 안되고 테스트끝나면 지워지게해줌.

class MemberServiceIntegrationTest {

//    테스트는 코드를 만들때는 제일 편한방법을씀( 생성자주입 안씀
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



//    @BeforeEach
//    public void beforeEach(){//테스트실행할때마다 독립적으로 만들어줌
//        memberRepository = new MemoryMemberRepository();
//        memberService= new MemberService(memberRepository);
//    }
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStroe();
//    }

    @Test
    void 회원가입() {
        //테스트는 한국어로적어도 됨
        //테스트가 길때는
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setName("spring");

        Member member2= new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e=assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

/*        try{
            memberService.join(member2);//예외가터져야함.
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다123");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}