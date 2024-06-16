package libraryfrontend.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import libraryfrontend.services.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, HttpSession session) {
		if (loginService.verifyUserCredentials(username, password)) {
			session.setAttribute("loggeduser", username);
			return "redirect:/home";
		} else {
			return "redirect:/";
		}
	}
}
