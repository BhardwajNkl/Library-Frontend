package libraryfrontend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import libraryfrontend.models.Author;
import libraryfrontend.services.AddService;


@Controller
public class AddController {
	@Autowired
	private AddService addService;
	
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session) {
		ModelAndView mv;
		if(session.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		mv = new ModelAndView();
		List<Author> authorList=addService.getAuthorList();
		mv.addObject("authorList",authorList);
		mv.setViewName("add");
		return mv;
	}
}
