
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
@Service
@Transactional
public class CommentService {
@Autowired
	private CommentRepository commentRepository;
public Collection<Comment>  findAll(){
return commentRepository.findAll();
}
public Comment findOne(Integer valueOf) {
return commentRepository.findOne(valueOf);
}
public Comment save(Comment comment){
return commentRepository.save(comment);
}

public Comment create(){
	Comment result;

	result = new Comment();

	return result;

}

public void delete(Comment comment) {
	Assert.notNull(comment);

	commentRepository.delete(comment);
}
}