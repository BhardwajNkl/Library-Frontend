package libraryfrontend.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import libraryfrontend.models.Author;
import libraryfrontend.models.Book;
import libraryfrontend.services.EditService;


@Controller
public class EditController {
	@Autowired
	private EditService editService;
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") String id, HttpSession session) {
		ModelAndView mv;
		if(session.getAttribute("loggeduser")==null) {
			mv = new ModelAndView("redirect:/");
			return mv;
		}
		
		Book book = editService.getBook(id);
		List<Author> authorList = editService.getAuthorList();
		
		mv = new ModelAndView();
		mv.addObject("book",book);
		mv.addObject("authorList",authorList);
		mv.setViewName("edit_page");
		
		return mv;
	}
}
