package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//구체적 인터페이스 의존 ->DIP위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();//구체적 인터페이스에 의존 ->DIP위반
//    레포지토리를 변경할떄 서비스의 코드도 변경 ->OCP위반

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;// 추상화인 인터페이스에만 의존함.
    @Autowired//생성자에서 여러 의존관계도 한번에 주입 받을 수 있다.
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //->DIP지킴 인터페이스에만 의존함 누군가가 뭘 넣을지 모르는것. FixDiscount가들어올지 RateDiscount가들어올지 모름
    //DIP 완성: MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다.
    // 이제 구체 클래스를 몰라도 된다.


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);//할인여부 체크
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
