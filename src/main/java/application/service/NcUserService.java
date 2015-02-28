package application.service;

import application.domain.NcUser;

public interface NcUserService {
	public NcUser createUser(NcUser user);
	public NcUser findById(int id);
	public NcUser findByName(String name);
}
