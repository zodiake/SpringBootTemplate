package Application.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SpringUserContext implements UserContext{

	public User getCurrnetUser() {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return (User)authentication.getPrincipal();
	}

}
