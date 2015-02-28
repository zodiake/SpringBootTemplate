package application.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import application.domain.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

}
