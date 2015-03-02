package application.security;

import application.domain.NcUser;

public interface UserContext {
	NcUser getCurrnetUser();
	void setCurrentUser(NcUser user);
}
