package couch.forrest.domain.love.dao;

import couch.forrest.domain.love.entity.Love;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoveRepository extends JpaRepository<Love, Long>,LoveRepositoryCustom {

}
