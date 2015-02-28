package application.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.domain.NcUser;
import application.service.NcUserService;

@Service("authService")
public class DefaultUserService implements UserDetailsService{
	@Autowired
	private NcUserService userService;

	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		NcUser user=userService.findByName(name);

		Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
		GrantedAuthority authority=new GrantedAuthority(){
			public String getAuthority() {
				return "ROLE_USER";
			}
			
		};
		authorities.add(authority);
		return new User(user.getName(),user.getPassword(),authorities);
	}

}
