package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository {

    // save 할 때 저장할 메모리
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // member들
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 돌려서 없으면 Optional에 null넣어서 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store을 비워주는 메소드
    public void clearStore() {
        store.clear();
    }
}
