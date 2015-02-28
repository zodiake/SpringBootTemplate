package application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import application.domain.NcUser;
import application.security.UserContext;
import application.service.NcUserService;

@Controller
public class SignupController {
	private static final String SIGNUP = "user/signup";
	private static final String HOME = "index";

	@Autowired
	private NcUserService userService;
	@Autowired
	private UserContext userContext;

	@RequestMapping(value = "/signup/new", method = RequestMethod.POST)
	private String signup(@ModelAttribute("user") @Valid NcUser user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return SIGNUP;
		}
		NcUser saved = userService.createUser(user);
		userContext.setCurrentUser(saved);
		return HOME;
	}

	@RequestMapping(value = "/signup/new", method = RequestMethod.GET)
	private String getSignup(Model uiModel){
		NcUser user=new NcUser();
		uiModel.addAttribute("user", user);
		return SIGNUP;
	}
}
