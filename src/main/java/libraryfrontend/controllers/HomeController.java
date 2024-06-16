package libraryfrontend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import libraryfrontend.models.Book;
import libraryfrontend.services.HomeService;

@Controller
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mv;
		if(session.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		mv = new ModelAndView();
		
		List<Book> bookList=homeService.getBookList();
		
		mv.addObject("bookList",bookList);
		mv.setViewName("home");
		
		return mv;
	}
}
