package application.service;

import application.domain.Post;

public interface PostService {
	public Post save(Post post);
	public Post findById(int id);
	public Post update(int id,Post newPost);
	public void incrRaise(int id);
}
