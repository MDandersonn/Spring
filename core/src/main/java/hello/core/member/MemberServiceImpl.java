package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
//    private final MemberRepository memberRepository= new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    @Autowired//ac.getBean(MemberRepository.class) 자동으로 이코드가 들어간다고보면된다
    // 자동 의존관계주입 : 스프링이 MemberRepository타입에 맞는애를 찾아와서 의존관계 주입을 자동으로해줌
    public MemberServiceImpl(MemberRepository memberRepository) {
        //@Component를  MemoryMemberRepository에 적어줬으니 이것이 주입됨.
        this.memberRepository = memberRepository;
    }//생성자를통해서 멤버리포지토리 구현체가 뭐가들어갈지 결정

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
