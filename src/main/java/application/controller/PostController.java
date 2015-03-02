package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import application.domain.Post;
import application.exception.NoAuthorityException;
import application.exception.PostNotFoundException;
import application.security.UserContext;
import application.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserContext userContext;

	private static final String POSTDETAIL = "post/view";
	private static final String POSTEDIT = "post/edit";

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model uiModel) {
		Post post = postService.findById(id);
		uiModel.addAttribute("post", post);
		return POSTDETAIL;
	}

	@RequestMapping(value = "/center/posts/{id}", params = "form", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id) {
		Post post = postService.findById(id);
		if (post == null)
			throw new PostNotFoundException();
		if(!post.getCreatedBy().equals(userContext.getCurrnetUser()))
			throw new NoAuthorityException();
		return "";
	}
}