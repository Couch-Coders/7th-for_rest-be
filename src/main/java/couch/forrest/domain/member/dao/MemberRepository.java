package couch.forrest.domain.member.dao;

import couch.forrest.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUid(String uid);

    Optional<Object> findByEmail(String email);
}
