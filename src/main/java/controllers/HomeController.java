package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Book;
import services.HomeService;

@Controller
public class HomeController {
	HomeService hs;
	@RequestMapping("/home")
	public ModelAndView home(HttpSession s) {
		ModelAndView mv;
		if(s.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		mv = new ModelAndView();
		hs = new HomeService();
		List<Book> bookList=hs.getData();
		mv.addObject("bookList",bookList);
		mv.setViewName("home");
		return mv;
	}
}
