package Application.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Application.domain.NcUser;

@Controller
public class SignupController {
	private static final String SIGNUP = "signup";
	private static final String HOME = "home";

	@RequestMapping(value = "/signup/new", method = RequestMethod.POST)
	private String signup(@ModelAttribute("user") @Valid NcUser user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return SIGNUP;
		}
		
		return HOME;
	}
}
