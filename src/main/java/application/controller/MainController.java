package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import application.security.UserContext;

@Controller
public class MainController {
	@Autowired
	private UserContext securityContext;

	@RequestMapping(value = "/")
	public String index(Model uiModel) {
		Page page=new Page(7,15);
		uiModel.addAttribute("page", page);
		return "home/index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home")
	public String home() {
		String name = securityContext.getCurrnetUser().getName();
		return null;
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
