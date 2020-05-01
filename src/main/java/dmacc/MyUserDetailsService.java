package dmacc;

import dmacc.beans.AuthGroup;
import dmacc.beans.MyUserDetails;
import dmacc.beans.Users;
import dmacc.repository.AuthGroupRepository;
import dmacc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	private AuthGroupRepository authGroupRepository;
	
	public MyUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
		super();
		this.userRepository = userRepository;
		this.authGroupRepository = authGroupRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = this.userRepository.findByEmail(email);
		if (null == user) {
			throw new UsernameNotFoundException("Cannot find username: " + email);
		}
		List<AuthGroup> authGroups = this.authGroupRepository.findByEmail(email);
		return new MyUserDetails(user, authGroups);
	}

}