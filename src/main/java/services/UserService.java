package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import domain.Ban;
import domain.User;

@Service
@Transactional
public class UserService {

	// Managed repository --------------------
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BanService banService;
	
	public Collection<User>  findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Integer valueOf) {
		return userRepository.findOne(valueOf);
	}
	

	//Other business methods -------------------

	public User create(){
		User result;
		Ban res;
		result = new User();
		res = banService.create();
		result.setBan(res);
		return result;
	}
	
	public User save(User user){
		Assert.notNull(user);	
		return userRepository.save(user);
	}
	
	public void delete(User user){
		Assert.notNull(user);
		banService.delete(user.getBan());
		userRepository.delete(user);
	}
	
	public User findByPrincipal() {
	// TODO Auto-generated method stub
		return userRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
	}
	
	public User findByUsername(String username) {
	// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	
}