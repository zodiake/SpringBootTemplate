package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import application.domain.Post;
import application.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	private static final String POSTDETAIL="post/view";

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id,Model uiModel) {
		Post post=postService.findById(id);
		uiModel.addAttribute("post", post);
		return POSTDETAIL;
	}

	@RequestMapping(value = "/posts/{id}", params="form",method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id) {
		return "";
	}
}
