package application.security;

import org.springframework.security.core.userdetails.User;

import application.domain.NcUser;

public interface UserContext {
	User getCurrnetUser();
	void setCurrentUser(NcUser user);
}
