package servicesTest;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.CommentService;
import services.ThreadService;
import services.UserService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Thread;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CommentServiceTest extends AbstractTest {
	// Service undertest ------------------------------------------------------
	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ThreadService threadService;

	// Tests ------------------------------------------------------------------
	@Test
	public void testFindAll() {
		Collection<Comment> all;

		all = commentService.findAll();

		for (Comment a : all) {
			System.out.println(a.getText());
		}
	}

	@Test
	public void testFindOne() {
		Comment c;
		Integer commentId = 15;

		c = commentService.findOne(commentId);

		System.out.println(c.getText());

	}

	@Test
	public void testCreate() {
		Comment i;

		i = commentService.create();

		System.out.println(i);
	}

	@Test
	public void testSave() {
		Comment c;
		User u;
		Thread t;
		Date d;

		u = userService.findOne(10);
		t = threadService.findOne(13);
		d = new Date();
		
		c = commentService.create();

		c.setText("Nuevo texto");
		c.setUser(u);
		c.setThread(t);
		c.setCreationMoment(d);

		commentService.save(c);

		System.out.println(c.getText());
	}

	@Test
	public void testDelete() {
		Comment t = commentService.findOne(15);

		commentService.delete(t);
		System.out.println("eliminado");
	}

}
