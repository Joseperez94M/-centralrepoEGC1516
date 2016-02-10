package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Ban;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer>{

}
