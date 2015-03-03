package controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import application.Application;
import application.domain.Page;
import application.domain.Post;
import application.service.PostService;
import application.utils.PageUtils;

@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext
public class PostControllerTest {
	private PostService postService;
	private List<Post> posts;

	@Before
	public void initContacts() {
		posts=new ArrayList<Post>();
		for(int i=0;i<15;i++){
			Post post=new Post();
			posts.add(post);
		}
	}

	@Test
	public void testList() {
		postService = mock(PostService.class);
		when(postService.findAll(mock(Pageable.class))).thenReturn(posts);
		when(postService.countAll()).thenReturn(100L);
		PageImpl<Post> pageImpl=new PageImpl<Post>(posts,new PageRequest(17,15),300);
		Page p=PageUtils.caculatePage(pageImpl);
		assertEquals(13,p.getBegin());
		assertEquals(20,p.getEnd());
	}
}
