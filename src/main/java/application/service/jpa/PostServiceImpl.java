package application.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.domain.Content;
import application.domain.NcUser;
import application.domain.Post;
import application.repository.PostRepository;
import application.service.PostService;

import com.google.common.collect.Lists;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@PersistenceContext
	private EntityManager em;

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post findById(int id) {
		Post post = postRepository.findOne(id);
		if (post != null) {
			post.getContent();
			post.getCreatedBy();
		}
		return post;
	}

	@Override
	public Post update(int id, Post newPost) {
		Post old = postRepository.findOne(id);
		old.setTitle(newPost.getTitle());
		Content content = old.getContent();
		content.setContent(newPost.getContent().getContent());
		return postRepository.save(old);
	}

	@Override
	public void incrRaise(int id) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("post.updateRaise");
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@Override
	public List<Post> findAll(Pageable pageable) {
		return Lists.newLinkedList(postRepository.findAll(pageable));
	}

	@Override
	public long countAllByCreatedBy(NcUser user) {
		return postRepository.countByCreatedBy(user);
	}

	@Override
	public long countAll() {
		return postRepository.count();
	}
}
