package Application.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authService")
public class DefaultUserService implements UserDetailsService{

	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
		GrantedAuthority authority=new GrantedAuthority(){
			public String getAuthority() {
				return "ROLE_USER";
			}
			
		};
		authorities.add(authority);
		return new User("tom","11",authorities);
	}

}
