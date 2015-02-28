package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import application.security.UserContext;

@Controller
public class MainController {
	@Autowired
	private UserContext securityContext;
	
	@RequestMapping(value="/")
	public String index(){
		return "home/index";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/home")
	public String home(){
		String name=securityContext.getCurrnetUser().getUsername();
		System.out.println(name);
		return null;
	}

}
