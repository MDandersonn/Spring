package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();
    /*
  save를 할때 저장을 어디에 해놔야겠죠? 메모리에.  Map을 씀 key는 회원의 id(type:Long) 값은 member
  실무에서는 동시성문제가있을수있어서 컹커르해쉬맵?을 사용해야하는데 예시니까 해쉬맵사용.
  시퀀스는 0,1,2.. 키값을 생성해주는 것
  실무에서는 동시성문제를 고려해서 어텀롱 등등 해야하는데 단순하게 이렇게 하겠습니다.
     */

    private static long sequence= 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //결과가없으면 null이겠죠  이럴가능성이있으면 옵셔널로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//결과가옵셔널로반환됨 루프를돌면서 하나 찾아지면 그대로 반환함. 끝까지도는데 없으면 옵셔널에 널이포함되서 반환

    }

    @Override
    public List<Member> findAll() {
        System.out.println(store.values());
        return new ArrayList<>(store.values());   //Map객체에 있는 value(Member)들 쫙 반환.
    }

    public void clearStroe(){
        store.clear();

    }
}
