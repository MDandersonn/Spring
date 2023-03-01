package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){//테스트실행할때마다 독립적으로 만들어줌
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStroe();
    }

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