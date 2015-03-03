package application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import application.domain.NcUser;
import application.domain.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
	public Page<Post> findByCreatedBy(NcUser user,Pageable pageable);
	public long countByCreatedBy(NcUser user);
}
