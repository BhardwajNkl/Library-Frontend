package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.LoginVerfier;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, HttpSession s) {
		// get object of login verifier
		LoginVerfier lv = new LoginVerfier();
		if (lv.verifyUser(username, password)) {
			s.setAttribute("loggeduser", username);
			return "redirect:/home";
		} else {
			return "redirect:/";
		}
	}
}
