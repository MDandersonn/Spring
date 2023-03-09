package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//회원을 저장하면 저장한 회원이 반환
    Optional<Member> findById(Long id);//id로 회원을 찾음
//optional은 자바8에 들어간 기능이다.
    Optional<Member> findByName(String name);//이름으로 회원찾음
    List<Member> findAll();//저장된 모든 회원리스트 반환.
}
