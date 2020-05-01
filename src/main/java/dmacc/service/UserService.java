package dmacc.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dmacc.beans.AuthGroup;
import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;
import dmacc.beans.Users;
import dmacc.beans.UserDto;
import dmacc.repository.AuthGroupRepository;
import dmacc.repository.TeamManagerRepository;
import dmacc.repository.TeamMemberRepository;
import dmacc.repository.UserRepository;
import dmacc.service.UserAlreadyExistException;

@Service
public class UserService implements IUserService {
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private TeamManagerRepository tManagerRepo;
    
    @Autowired
    private TeamMemberRepository tMemberRepo;
    
    @Autowired
    private AuthGroupRepository authGroupRepo;
    
    public Users findByEmail(String email){
        return userRepo.findByEmail(email);
    }
    
    public Users registerNewUserAccount(UserDto userDto) {
        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        AuthGroup authGroup = new AuthGroup();
        authGroup.setEmail(userDto.getEmail());
        
        if (userDto.getRegistrationType().equals("addteammember")) {
        	TeamMember teamMember = new TeamMember();
        	teamMember.setFirstName(userDto.getFirstName());
        	teamMember.setLastName(userDto.getLastName());
        	teamMember.setUser(user);
        	authGroup.setAuthGroup("USER");
        	tMemberRepo.save(teamMember);
        } else {
	        TeamManager teamManager = new TeamManager();
	        teamManager.setFirstName(userDto.getFirstName());
	        teamManager.setLastName(userDto.getLastName());
	        teamManager.setUser(user);
	        authGroup.setAuthGroup("ADMIN");
	        tManagerRepo.save(teamManager);
        }
        
        authGroupRepo.save(authGroup);
        
        return user;
    }
}
