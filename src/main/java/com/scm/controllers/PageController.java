package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class PageController {

    @Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("name", "Vishnukanth");
		model.addAttribute("Role", "Software Developer");
		System.out.println("Home Page Handler");
		return "home";
	}

	@GetMapping("/")
	public String index(){
		return "redirect:/home";
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
	public String registerPage(Model model) {
		UserForm userForm=new UserForm();
		model.addAttribute("userForm", userForm);
		return "register";
	}

	//Processing Register

	@PostMapping(value="/do-register")
	public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session) {
		System.out.println("Processing Registration");
		System.out.println(userForm);
		//fetch form data

		//validate form data
		if(rBindingResult.hasErrors()){
			return "register";
		}

		//save to database
		/* User user=User.builder()
		.name(userForm.getName())
		.email(userForm.getEmail())
		.phoneNumber(userForm.getPhoneNumber())
		.about(userForm.getAbout())
		.password(userForm.getPassword())
		.build(); */
        User user=new User();
		user.setName(userForm.getName());
		user.setAbout(userForm.getAbout());
		user.setEmail(userForm.getEmail());
		user.setPhoneNumber(userForm.getPhoneNumber());
        user.setPassword(userForm.getPassword());
        User savedUser= userService.saveUser(user);
		System.out.println("user saved");
		//message
       Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
		session.setAttribute("message", message);

		//redirect
		return "redirect:/register";
	}
	
	

}
