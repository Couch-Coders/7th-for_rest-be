package couch.forrest.domain.love.dao;

import couch.forrest.domain.love.entity.Love;
import couch.forrest.domain.member.entity.Member;
import couch.forrest.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface LoveRepository extends JpaRepository<Love, Long>,LoveRepositoryCustom {

    Optional<Love> findByMemberAndPlace(Member member, Place place);
}
