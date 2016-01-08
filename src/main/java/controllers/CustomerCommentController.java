package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;

import services.CommentService;

@Controller
@RequestMapping("/comment/customer")
public class CustomerCommentController extends AbstractController{

		// Services ---------------------------------------------------------------
	
		@Autowired CommentService commentService;
			
		// Constructors -----------------------------------------------------------
		
		public CustomerCommentController() {
			super();
		}
		
		// Listing ----------------------------------------------------------------
		
		
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
		public ModelAndView edit(@RequestParam int id) {		
			ModelAndView result;
			Comment comment;
			
			comment = commentService.findOne(id);		
			result = createEditModelAndView(comment);
			
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView saveComment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult) {
			ModelAndView result;		
					
			if (bindingResult.hasErrors()) {
				result = createEditModelAndView(comment);
			} else {
				try {			
					commentService.save(comment);
					result = new ModelAndView("redirect:customer/listThreads.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(comment, "comment.commit.error");				
				}
			}
			
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@ModelAttribute Comment comment, BindingResult bindingResult) {
			ModelAndView result;		
			
			try {			
				commentService.delete(comment);
				result = new ModelAndView("redirect:listThreads.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, "comment.commit.error");			
			}
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Comment comment) {
			assert comment != null;
			
			ModelAndView result;

			result = createEditModelAndView(comment, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Comment comment, String message) {
			assert comment != null;
			
			ModelAndView result;				

			result = new ModelAndView("comment/edit");
			result.addObject("comment", comment);
			result.addObject("message", message);
			
			return result;
		}
	
}