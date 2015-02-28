package Application.repository;

import org.springframework.data.repository.CrudRepository;

import Application.domain.NcUser;

public interface NcUserRepository extends CrudRepository<NcUser, Integer> {
	
}
