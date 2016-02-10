/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.BanService;
import services.ThreadService;
import services.UserService;

import domain.Administrator;
import domain.User;
import domain.Thread;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private BanService banService;

	@Autowired
	private ThreadService threadService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	// Listing ----------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<User> users;

		users = userService.findAll();

		result = new ModelAndView("administrator/list");
		result.addObject("requestURI", "administrator/list.do");
		result.addObject("users", users);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int userId) {
		ModelAndView result;
		User user;

		user = userService.findOne(userId);
		result = createEditModelAndView(user);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid User user, BindingResult binding) {
		ModelAndView result;

		User user1 = userService.findOne(user.getId());
		try {
			if (user1.getBan().getBanned() == true) {
				user1.getBan().setBanned(false);
			} else {
				user1.getBan().setBanned(true);
			}
			banService.save(user1.getBan());
			result = new ModelAndView("redirect:/administrator/list.do");
		} catch (Exception oops) {
			// result = createEditModelAndView(user, "user.commit.error");
			throw oops;
		}

		return result;
	}
	

	// ---------Crear, Modificar y borrar Threads----------

	@RequestMapping(value = "/createThread", method = RequestMethod.GET)
	public ModelAndView createThread() {
		ModelAndView result;
		Thread thread;
		Administrator admin;
		admin = administratorService.findByPrincipal();
		Assert.notNull(admin);
		thread = threadService.create();

		result = createEditModelAndView2(thread);

		return result;

	}
	
	@RequestMapping(value = "/editThread", method = RequestMethod.GET)
	public ModelAndView editThread(@RequestParam int id) {

		ModelAndView result;
		Thread thread;

		thread = threadService.findOne(id);
		Assert.notNull(thread);
		result = createEditModelAndView2(thread);

		return result;
	}

	@RequestMapping(value = "/editThread", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Thread thread,
			BindingResult binding) {
		Administrator admin;
		admin = administratorService.findByPrincipal();
		Assert.notNull(admin);
		ModelAndView result;
		result = new ModelAndView("redirect:/customer/listThreads.do");

		try {
			threadService.save(thread);
		} catch (Throwable oops) {
			result = createEditModelAndView2(thread,
					"administrator.thread.commit.error");
		}
		return result;

	}

	@RequestMapping(value = "/editThread", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Thread thread, BindingResult binding) {
		Administrator admin;
		admin = administratorService.findByPrincipal();
		Assert.notNull(admin);
		ModelAndView result;

		try {
			threadService.delete(thread);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView2(thread,
					"administrator.thread.commit.error");
		}
		return result;

	}

	// Ancillary methods------------------------------

	protected ModelAndView createEditModelAndView(User user) {
		ModelAndView result;

		result = createEditModelAndView(user, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(User user, String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/edit");
		result.addObject("user", user);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Thread thread) {
		ModelAndView result;

		result = createEditModelAndView2(thread, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(Thread thread, String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/editThread");
		result.addObject("thread", thread);
		result.addObject("message", message);

		return result;
	}

}