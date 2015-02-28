package Application.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.domain.Post;
import Application.repository.PostRepository;
import Application.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post findById(int id) {
		Post post = postRepository.findOne(id);
		post.getContent();
		return post;
	}

}
