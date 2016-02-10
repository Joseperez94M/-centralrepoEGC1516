package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ThreadRepository;
import domain.Administrator;
import domain.Thread;


@Service
@Transactional
public class ThreadService {

	// Managed repository --------------------
	@Autowired
	private ThreadRepository threadRepository;
	
	@Autowired
	private AdministratorService administratorService;
	

	// Simple CRUD methods ----------
	public Thread create(){
		Thread result;
		Administrator administrator;
		Date creationMoment;
		
		creationMoment= new Date();
		administrator = administratorService.findByPrincipal();

		result= new Thread();
		result.setAdministrator(administrator);
		result.setCreationMoment(creationMoment);
		
		return result;
		
	}
	
	public Thread save(Thread thread){
		return threadRepository.save(thread);
	}
	
	public Collection<Thread>  findAll(){
		return threadRepository.findAll();
	}
	
	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
	}
	
	public void delete(Thread thread){
		int id;
		id=thread.getId();
		threadRepository.delete(id);
	}
	
	//Other business methods -------------------
	public Thread findThreadByComment(int commentId) {
		return threadRepository.findThreadByComment(commentId);
	}
}