package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;
// 이건뭐냐면 JPA는 entityManager라는걸로 모듈이 동작함.
    //스프링이 자동으로 entityManager를 생성해줌 이걸 우리는 인젝션하면됨
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist(영구저장하다) -> jpa가 인서트쿼리 다 만들어서 db에 집어넣고 셋id까지 다해줌
        return member;//리턴값은 대충
    }
    public Optional<Member> findById(Long id) {
//        한개를 찾을때는 이렇게.
        Member member = em.find(Member.class, id); // id조회  (조회할타입이랑 , 식별자pk값) 넣어주면됨
        return Optional.ofNullable(member); // 값이없을수도있으니  옵셔널로 반환
    }
    public List<Member> findAll() {
        //        JPQL이라는 객체지향쿼리를 써야한다
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name1) {
//        여러개를 리스트로 받을때는
//        JPQL이라는 객체지향쿼리를 써야한다
        //테이블대상으로 SQL을 날리는게아니라 객체(멤버entity)를 대상으로 쿼리를 날리면 SQL로 변형이됨 .
        //멤버entity를 대상으로 조회, select m 은 객체자체를 조회한다는얘기
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name1)
                .getResultList();
        return result.stream().findAny(); //하나만 찾으려고하니까 하나찾는순간 끝내려고.
    }
}

