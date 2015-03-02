package application.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.domain.Post;
import application.repository.PostRepository;
import application.service.PostService;

@Service
@Transactional
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
		if (post != null){
			post.getContent();
			post.getCreatedBy();
		}
		return post;
	}

	@Override
	public Post update(int id, Post newPost) {
		Post old=postRepository.findOne(id);
		old.setTitle(newPost.getTitle());
		old.setContent(newPost.getContent());
		return postRepository.save(old);
	}
}
