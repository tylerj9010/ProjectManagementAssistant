package dmacc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "projectdashboard.html";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@RequestMapping("/logout")
	public String logoutPage() {
		return "logout.html";
	}
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration.html";
	}
}
