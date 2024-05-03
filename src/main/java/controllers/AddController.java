package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Author;
import services.AddService;

@Controller
public class AddController {
	AddService as;
	@RequestMapping("/add")
	public ModelAndView add(HttpSession s) {
		ModelAndView mv;
		if(s.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		mv = new ModelAndView();
		as = new AddService();
		List<Author> authorList=as.getData();
		mv.addObject("authorList",authorList);
		mv.setViewName("add");
		return mv;
	}
}
