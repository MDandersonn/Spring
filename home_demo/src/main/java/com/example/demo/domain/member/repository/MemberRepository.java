package com.example.demo.domain.member.repository;

/*
eager로할땐 fetch만 썼었죠  lazy로 할땐 join fetch 사용하고있습니다
 */

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


//    @Query("select m from Member m where m.email = :email")
//    Optional<Member> findByEmail(String email);

    // FETCH TYPE LAZY에 의해 proxy 참조가 안되서 join fetch로 강제 참조시킴
    @Query("select m from Member m join fetch m.authentications where m.email = :email")
    Optional<Member> findByEmail(String email);
}
