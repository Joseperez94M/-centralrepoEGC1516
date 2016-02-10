package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.ActorService;
import utilities.AbstractTest;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/datasource.xml",
								"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class ActorServiceTest extends AbstractTest{
	// Service undertest ------------------------------------------------------
	@Autowired
	private ActorService actorService;
	
	// Tests ------------------------------------------------------------------
	@Test
	public void testFindAll(){
		Collection<Actor> all;
		
		all=actorService.findAll();
		
		for(Actor a: all){
			System.out.println(a.getName());
		}
	}
	
	@Test
	public void testFindOne(){
		Actor a;
		Integer actorId=10;
		
		a=actorService.findOne(actorId);
		
		System.out.println(a.getName());
		
	}
	
	
	@Test
	public void testSave() {
		Actor a;
		
		a = actorService.findOne(10);

		a.setName("Nombre");

		actorService.save(a);

		System.out.println(a.getName());
	}

}
