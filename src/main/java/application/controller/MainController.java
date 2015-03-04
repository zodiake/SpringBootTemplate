package application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import application.security.UserContext;

@Controller
public class MainController {
	@Autowired
	private UserContext securityContext;
	private static final String HOME="home/index";

	@RequestMapping(value = "/")
	public String index(Model uiModel) {
		Page page=new Page(7,15);
		uiModel.addAttribute("page", page);
		return HOME;
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home")
	public String home() {
		String name = securityContext.getCurrnetUser().getName();
		return HOME;
	}

	private class Page {
		int total;
		int page;

		public Page(int total, int page) {
			this.total = total;
			this.page = page;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

	}

}
