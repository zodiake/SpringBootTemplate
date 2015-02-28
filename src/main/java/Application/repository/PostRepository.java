package Application.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import Application.domain.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

}
