package Application.security;

import org.springframework.security.core.userdetails.User;

import Application.domain.NcUser;

public interface UserContext {
	User getCurrnetUser();
	void setCurrentUser(NcUser user);
}
