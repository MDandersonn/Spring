package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // JPA를 쓰려면 항상있어야함 //데이터를 저장하거나 변경할때 항상있어야함
/*
* 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을
커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다. 그래서 여기에 @트랜잭셔널 을 적어주는거임
* */
public class MemberService {

    private final MemberRepository memberRepository;

    //    @Autowired// 이걸 생성자위에 쓰면 생성자주입이다.  (필드주입 ,세터주입은 안좋다)
    //조립시점에 생성자로 한번만 딱 조립해놓고 끝내고 그다음에 변경못하게 막아버림.
    public MemberService(MemberRepository memberRepository) {
        //생성자주입
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member);//같은이름있는 중복회원X
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        //null가능성있으면Optional로 감싸면됨
        result.ifPresent(m -> { //null이 아닐때.(이미 name이 존재하는경우)
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    //서비스는 비지니스 관련네임을 사용해야함
    //리포지토리는 서비스보다는 단순히 기계적으
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }


}
