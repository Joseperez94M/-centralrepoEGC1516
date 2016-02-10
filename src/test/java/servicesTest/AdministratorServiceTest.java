package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.AdministratorService;
import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AdministratorServiceTest extends AbstractTest {
	// Service undertest ------------------------------------------------------
	@Autowired
	private AdministratorService adminService;

	// Tests ------------------------------------------------------------------
	@Test
	public void testFindAll() {
		Collection<Administrator> all;

		all = adminService.findAll();

		for (Administrator a : all) {
			System.out.println(a.getName());
		}
	}

	@Test
	public void testFindOne() {
		Administrator a;
		Integer adminId = 9;

		a = adminService.findOne(adminId);

		System.out.println(a.getName());

	}

	@Test
	public void testSave() {
		Administrator a;

		a = adminService.findOne(9);

		a.setName("Nombre");

		adminService.save(a);

		System.out.println(a.getName());
	}

}
