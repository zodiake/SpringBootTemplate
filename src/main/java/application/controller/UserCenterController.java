package application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import application.domain.ChangePasswordForm;
import application.security.UserContext;
import application.service.NcUserService;

@Controller
public class UserCenterController {
	private static final String CHANGEPASSWORD = "user/changePassword";

	@Autowired
	private NcUserService userService;
	@Autowired
	private UserContext userContext;

	@RequestMapping(value = "/center/changePassword", method = RequestMethod.GET)
	public String changePassword(Model uiModel) {
		uiModel.addAttribute("form", new ChangePasswordForm());
		return CHANGEPASSWORD;
	}

	@RequestMapping(value = "/center/changePassword", method = RequestMethod.POST)
	public String changePasswordPost(
			@ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult result, Model uiModel) {
		if(result.hasErrors()){
			uiModel.addAttribute("form", new ChangePasswordForm());
			return CHANGEPASSWORD;
		}
		userService.changePassword(userContext.getCurrnetUser(),form);
		return "redirect:/success";
	}
}
