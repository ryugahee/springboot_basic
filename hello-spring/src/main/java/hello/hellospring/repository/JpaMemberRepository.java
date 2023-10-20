package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    // 엔티티를 관리하는 객체
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // DB에서 pk가 id인 member 조회(식별자로 조회)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);  // member 객체를 Optional로 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 엔티티 대상으로 쿼리를 날림
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
