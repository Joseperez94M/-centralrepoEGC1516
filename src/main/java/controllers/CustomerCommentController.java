package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Comment;
import domain.User;
import services.ActorService;
import services.CommentService;
import services.ThreadService;
import services.UserService;
import domain.Comment;
import domain.User;

@Controller
@RequestMapping("/comment/customer")
public class CustomerCommentController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	CommentService commentService;

	@Autowired
	ActorService actorService;

	@Autowired
	ThreadService threadService;

	@Autowired
	UserService userService;

	// Constructors -----------------------------------------------------------
	public CustomerCommentController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Comment> comments;
		User user;
		user = userService.findByPrincipal();
		comments = commentService.findCommentByUser(user);

		result = new ModelAndView("comment/customer/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "/comment/customer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Comment comment;

		comment = commentService.create();
		result = createEditModelAndView(comment);

		return result;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int commentId) {
		ModelAndView result;
		Comment comment;
		User user;

		user = userService.findByPrincipal();
		comment = commentService.findOne(commentId);
		Assert.isTrue(comment.getUser().equals(user));
		result = createEditModelAndView(comment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(comment);
		} else {
			try {
				commentService.save(comment);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, "comment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Comment comment) {
		ModelAndView result;
		try {
			commentService.delete(comment);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(comment, "comment.commit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------------------------
	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;

		result = createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Comment comment,
			String message) {

		ModelAndView result;

		result = new ModelAndView("comment/customer/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
}