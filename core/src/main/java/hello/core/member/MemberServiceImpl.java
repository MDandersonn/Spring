package hello.core.member;

public class MemberServiceImpl implements MemberService{
//    private final MemberRepository memberRepository= new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
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
}
