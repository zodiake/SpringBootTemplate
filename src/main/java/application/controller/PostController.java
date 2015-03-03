package application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import application.domain.Message;
import application.domain.Post;
import application.exception.NoAuthorityException;
import application.exception.PostNotFoundException;
import application.security.UserContext;
import application.service.PostService;
import application.utils.PageUtils;

@Controller
public class PostController extends AbstractController<Post> {
	@Autowired
	private PostService postService;
	@Autowired
	private UserContext userContext;

	private static final String POSTDETAIL = "post/view";
	private static final String POSTEDIT = "post/edit";
	private static final String POSTCREATE = "post/create";
	private static final String POSTLIST = "post/lists";

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model uiModel) {
		Post post = postService.findById(id);
		if (post == null)
			throw new PostNotFoundException();
		uiModel.addAttribute("post", post);
		return POSTDETAIL;
	}

	// get a form to edit
	@RequestMapping(value = "/center/posts/{id}", params = "form", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model uiModel) {
		Post post = postService.findById(id);
		postRetrivePost(post);
		uiModel.addAttribute("post", post);
		return POSTEDIT;
	}

	// deal with update form
	@RequestMapping(value = "/center/posts/{id}", params = "form", method = RequestMethod.PUT)
	public String update(@PathVariable("id") Integer id,
			@ModelAttribute("post") Post post, BindingResult result,
			RedirectAttributes redirectAttributes, Model uiModel) {
		Post savedPost = postService.findById(id);
		postRetrivePost(savedPost);
		if (result.hasErrors()) {
			uiModel.addAttribute("post", post);
			return POSTEDIT;
		}
		postService.update(id, post);
		redirectAttributes.addFlashAttribute("message", new Message("success",
				"创建成功"));
		return "redirect:/center/posts/" + id + "?form";
	}

	// get a form to create
	@RequestMapping(value = "/center/posts", params = "form", method = RequestMethod.GET)
	public String create(Model uiModel) {
		Post post = new Post();
		uiModel.addAttribute("post", post);
		return POSTCREATE;
	}

	// deal with create new post
	@RequestMapping(value = "/center/posts", params = "form", method = RequestMethod.POST)
	public String createForm(@ModelAttribute("post") @Valid Post post,
			BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			uiModel.addAttribute("post", post);
			redirectAttributes.addFlashAttribute("message", new Message(
					"error", "创建失败"));
			return POSTCREATE;
		}
		post.setCreatedBy(userContext.getCurrnetUser());
		Post saved = postService.save(post);
		redirectAttributes.addFlashAttribute("message", new Message("success",
				"创建成功"));
		return "redirect:/center/posts/" + saved.getId() + "?form";
	}

	@RequestMapping(value = "/posts/praise", method = RequestMethod.POST)
	@ResponseBody
	public void updateRaise(@RequestParam("id") int id) {
		postService.incrRaise(id);
	}

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String lists(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "15") int size,
			Model uiModel) {
		PageRequest pageRequest = createPageRequest(page, size);
		List<Post> posts = postService.findAll(pageRequest);
		PageImpl<Post> pageImpl=createPageImpl(pageRequest, posts, postService.countAll());
		uiModel.addAttribute("posts", posts);
		uiModel.addAttribute("page", PageUtils.caculatePage(pageImpl));
		return POSTLIST;
	}

	private void postRetrivePost(Post post) {
		if (post == null)
			throw new PostNotFoundException();
		if (post.getCreatedBy().getId() != userContext.getCurrnetUser().getId())
			throw new NoAuthorityException();
	}

}