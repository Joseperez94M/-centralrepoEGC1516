package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Managed repository --------------------
	@Autowired
	private ActorRepository actorRepository;

	
	// Simple CRUD methods ----------
	public Actor save(Actor actor){
		return actorRepository.save(actor);
	}
	
	public Collection<Actor>  findAll(){
		return actorRepository.findAll();
	}
	
	public Actor findOne(int actorId) {
		return actorRepository.findOne(actorId);
	}
}