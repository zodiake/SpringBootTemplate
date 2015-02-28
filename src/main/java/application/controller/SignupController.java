package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import application.domain.NcUser;
import application.domain.SignupForm;
import application.security.UserContext;
import application.service.NcUserService;

import com.google.code.kaptcha.Constants;

@Controller
public class SignupController {
	private static final String SIGNUP = "user/signup";
	private static final String HOME = "index";

	@Autowired
	private NcUserService userService;
	@Autowired
	private UserContext userContext;

	@RequestMapping(value = "/signup/new", method = RequestMethod.POST)
	private String signup(@ModelAttribute("user") @Valid SignupForm user,
			BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors() || !validateCaptcha(user, request)
				|| !validatePassword(user)) {
			SignupForm post = postHandleForm(user);
			model.addAttribute("user", post);
			return SIGNUP;
		}
		NcUser saved = userService.transformFromSignupForm(user);
		userContext.setCurrentUser(saved);
		return HOME;
	}

	@RequestMapping(value = "/signup/new", method = RequestMethod.GET)
	private String getSignup(Model uiModel) {
		SignupForm form = new SignupForm();
		uiModel.addAttribute("user", form);
		return SIGNUP;
	}

	private boolean validateCaptcha(SignupForm user, HttpServletRequest request) {
		String captchaId = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		return StringUtils.equals(captchaId, user.getCaptcha());
	}

	private boolean validatePassword(SignupForm form) {
		return StringUtils.equals(form.getPassword(), form.getConfirmPassword());
	}

	private SignupForm postHandleForm(SignupForm form) {
		form.setCaptcha(null);
		form.setPassword(null);
		form.setConfirmPassword(null);
		return form;
	}
}
