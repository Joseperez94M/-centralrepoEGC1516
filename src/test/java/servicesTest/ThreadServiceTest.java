package servicesTest;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.ThreadService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Thread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ThreadServiceTest extends AbstractTest {
	// Service undertest ------------------------------------------------------
	@Autowired
	private ThreadService threadService;


	// Tests ------------------------------------------------------------------
	@Test
	public void testFindAll() {
		Collection<Thread> all;

		all = threadService.findAll();

		for (Thread a : all) {
			System.out.println(a.getTitle());
		}
	}

	@Test
	public void testFindOne() {
		Thread a;
		Integer threadId = 13;

		a = threadService.findOne(threadId);

		System.out.println(a.getTitle());

	}

	@Test
	public void testSave() {
		Collection<Comment> comments = new LinkedList<Comment>();
		Thread t;
		
		t = threadService.findOne(13);

		t.setComments(comments);
		t.setText("Thread");

		threadService.save(t);

		System.out.println(t.getText());
	}
	
}
