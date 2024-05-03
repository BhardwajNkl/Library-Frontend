package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Author;
import model.Book;
import services.EditService;

@Controller
public class EditController {
	private EditService es;
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") String id, HttpSession s) {
		ModelAndView mv;
		if(s.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		
		es = new EditService();
		Book book = es.getBook(id);
		List<Author> authorList = es.getAuthorList();
		mv = new ModelAndView();
		mv.addObject("book",book);
		mv.addObject("authorList",authorList);
		mv.setViewName("edit");
		return mv;
	}
}
