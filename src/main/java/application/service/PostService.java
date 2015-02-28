package application.service;

import application.domain.Post;

public interface PostService {
	public Post save(Post post);
	public Post findById(int id);
}
