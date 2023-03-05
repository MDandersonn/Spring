package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스는 extends로 다른 인터페이스를 다중상속이됨
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
                                                        //첫번째는 T . 테이블이라는얘긴가? 두번째는ID의 타입(식별자의 타입)
//    SpringDataJpa가 JpaRepository를 상속하고 있으면 SpringDataJpa가 인터페이스에 대한 구현체를 자동으로 만들어줌 그리고 스프링빈에 등록함
    //그리고 우리는 인젝션으로 받아서 SpringConfig파일의 멤버변수 memoryRepository에 등록을함
    //SpringDataJpa가 구현체를 만들어서 등록을해줌 우린 그것을 가져다쓰면됨
    @Override


    Optional<Member> findByName(String name);
    //이 메서드만 등록한 이유는 JPA에서 제공이안되기 때문에 ...
    //이렇게 이름만 등록을해도 아래처럼 쿼리를 짜준다.
    //메서드명을 이렇게 하면 findByName : select m from Member m where m.name=? 이렇게 규칙적으로 짜줌
    //findByNameAndId(String name , Long id)  이렇게도 할수있다 인터페이스 이름만으로도 sql을 짜는 효과.

}
