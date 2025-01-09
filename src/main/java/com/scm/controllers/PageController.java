package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("name", "Vishnukanth");
		model.addAttribute("Role", "Software Developer");
		System.out.println("Home Page Handler");
		return "home";
	}

    @RequestMapping("/about")
	public String aboutPage(){
		System.out.println("About Page");
		return "about";
	}

	@RequestMapping("/services")
	public String servicePage(){
		System.out.println("Services Page");
		return "services";
	}

	@GetMapping("/contact")
	public String ContactPage() {
		return "contact";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	

}
