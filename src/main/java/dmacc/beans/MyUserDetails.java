package dmacc.beans;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private List<AuthGroup> authGroups;
	
	public MyUserDetails(Users user, List<AuthGroup> authGroups) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authGroups = authGroups;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (null == authGroups) {
			return Collections.emptySet();
		}
		
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		authGroups.forEach(group -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
