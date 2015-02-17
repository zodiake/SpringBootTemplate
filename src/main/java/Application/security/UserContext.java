package Application.security;

import org.springframework.security.core.userdetails.User;

public interface UserContext {
	User getCurrnetUser();
}
