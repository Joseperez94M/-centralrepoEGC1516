package repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Integer> {

	@Query("select t from Thread t where t.comments=?1")
	Thread findThreadByComment(int commentId);
	
}
