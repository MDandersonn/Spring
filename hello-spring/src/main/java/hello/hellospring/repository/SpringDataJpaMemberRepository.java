package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
//    SpringDataJpa가 JpaRepository를 받고있으면 SpringDataJpa가 구현체를 자동으로 만들어줌
    //SpringDataJpa가 구현체를 만들어서 등록을해줌 우린 그것을 가져다쓰면됨
    @Override
//    findByName : select m from Member m where m.name=? 이렇게 규칙적으로 짜줌
    //findByNameAndId(String name , Long id)  이렇게도 할수있다 인터페이스 이름만으로도 sql을 짜는 효과.

    Optional<Member> findByName(String name);
    //인터페이스는 다중상속이됨
    //첫번째는 T T는 멤버 두번째는ID
}
