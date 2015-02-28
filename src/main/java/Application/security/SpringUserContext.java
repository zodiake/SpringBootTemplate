package Application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import Application.domain.NcUser;

@Service
public class SpringUserContext implements UserContext {
	@Autowired
	private UserDetailsService userDetailsService;

	public User getCurrnetUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return (User) authentication.getPrincipal();
	}

	public void setCurrentUser(NcUser user) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, user.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
