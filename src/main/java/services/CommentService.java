package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository --------------------
	@Autowired
	private CommentRepository commentRepository;

	
	// Simple CRUD methods ----------
	public Comment create(){
		Comment result;
		result = new Comment();
		return result;
	}

	public void save(Comment comment) {
        Assert.notNull(comment);
        commentRepository.save(comment);
    }
	public void delete(Comment comment) {
		Assert.notNull(comment);
		commentRepository.delete(comment);
	}
	
	public Collection<Comment>  findAll(){
		return commentRepository.findAll();
	}
	public Comment findOne(int commentId) {
		return commentRepository.findOne(commentId);
	}
	
	//Other business methods -------------------
	public Collection<Comment> findCommentByUser(User user) {
		return commentRepository.findCommentByUser(user);
	}
}