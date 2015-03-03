package application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import application.domain.NcUser;
import application.domain.Post;

public interface PostService {
	public Post save(Post post);
	public Post findById(int id);
	public Post update(int id,Post newPost);
	public void incrRaise(int id);
	public List<Post> findAll(Pageable pageable);
	public long countAllByCreatedBy(NcUser user);
	public long countAll();
}
