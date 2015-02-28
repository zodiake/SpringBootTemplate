package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.domain.NcUser;

public interface NcUserRepository extends CrudRepository<NcUser, Integer> {
	public NcUser findByName(String name);
}
