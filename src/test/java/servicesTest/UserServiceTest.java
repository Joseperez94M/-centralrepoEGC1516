package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.UserService;
import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UserServiceTest extends AbstractTest {
	// Service undertest ------------------------------------------------------
	@Autowired
	private UserService userService;

	// Tests ------------------------------------------------------------------
	@Test
	public void testFindAll() {
		Collection<User> all;

		all = userService.findAll();

		for (User u : all) {
			System.out.println(u.getName());
		}
	}

	@Test
	public void testFindOne() {
		User u;
		Integer userId = 10;

		u = userService.findOne(userId);

		System.out.println(u.getName());

	}

	@Test
	public void testSave() {
		User u;

		u = userService.findOne(10);

		userService.save(u);

		System.out.println(u.getName());
	}
	
	//@Test
	//public void testFindByPrincipal(){
		//User u;
		
		//authenticate("customer");
		
		//u = userService.findByPrincipal();
		//Falla porque hay que pasarle el usuario que está conectado y se le pasa en el servicio
		// y como esto es una prueba no hay nadie en la aplicación
		//System.out.println(u.getName());
	//}
	
	@Test
	public void testFindByUsername(){
		User u;
		
		u = userService.findByUsername("user1");
		
		System.out.println(u.getName());
	}

}
