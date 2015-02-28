package Application.service;

import Application.domain.Post;

public interface PostService {
	public Post save(Post post);
	public Post findById(int id);
}
