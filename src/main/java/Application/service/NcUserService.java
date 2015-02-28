package Application.service;

import Application.domain.NcUser;

public interface NcUserService {
	public NcUser createUser(NcUser user);
	public NcUser findById(int id);
	public NcUser findByName(String name);
}
