package application.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.domain.Authority;
import application.domain.NcUser;
import application.service.NcUserService;

@Service("authService")
public class DefaultUserService implements UserDetailsService {
	@Autowired
	private NcUserService userService;

	public UserDetails loadUserByUsername(String name) {
		NcUser user = userService.findByName(name);
		return new NcUserDetails(user);
	}

	private class NcUserDetails extends NcUser implements UserDetails {
		public NcUserDetails(NcUser user) {
			setId(user.getId());
			setPassword(user.getPassword());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			final Authority au = this.getAuthority();
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			GrantedAuthority authority = new GrantedAuthority() {
				public String getAuthority() {
					return au.getName();
				}

			};
			authorities.add(authority);
			return authorities;
		}

		@Override
		public String getUsername() {
			return this.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}
}
