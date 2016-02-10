package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BanRepository;

import domain.Ban;

@Service
@Transactional
public class BanService {
	
	@Autowired
	private BanRepository banRepository;
	

	public Collection<Ban>  findAll(){
		return banRepository.findAll();
	}
	
	public Ban findOne(Integer valueOf) {
		return banRepository.findOne(valueOf);
	}
	
	public Ban create(){
		Ban result;
		result = new Ban();
		result.setBanned(false);
		return result;
	}
	
	public Ban save(Ban ban){
		Assert.notNull(ban);	
		return banRepository.save(ban);
	}
	
	public void delete(Ban ban){
		Assert.notNull(ban);	
		banRepository.delete(ban);
	}
}
