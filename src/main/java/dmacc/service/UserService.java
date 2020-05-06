package dmacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dmacc.beans.AuthGroup;
import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;
import dmacc.beans.Users;
import dmacc.beans.UserDto;
import dmacc.beans.ChangeEmailPasswordDto;
import dmacc.repository.AuthGroupRepository;
import dmacc.repository.TeamManagerRepository;
import dmacc.repository.TeamMemberRepository;
import dmacc.repository.UserRepository;

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
    
    public TeamManager registerNewTeamManagerAccount(UserDto userDto) {
        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        TeamManager teamManager = new TeamManager();
        teamManager.setFirstName(userDto.getFirstName());
        teamManager.setLastName(userDto.getLastName());
        teamManager.setUser(user);
        
        tManagerRepo.save(teamManager);

    	AuthGroup authGroup = new AuthGroup();
        authGroup.setEmail(userDto.getEmail());
        authGroup.setAuthGroup("ADMIN");
        authGroupRepo.save(authGroup);
        
        return teamManager;
    }
    
    public TeamMember registerNewTeamMemberAccount(UserDto userDto) {
        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName();
	    TeamManager teamManager = tManagerRepo.findByUser(findByEmail(email));
        
    	TeamMember teamMember = new TeamMember();
    	teamMember.setFirstName(userDto.getFirstName());
    	teamMember.setLastName(userDto.getLastName());
    	teamMember.setUser(user);
    	teamMember.setTeamManager(teamManager);
    	
    	tMemberRepo.save(teamMember);

    	AuthGroup authGroup = new AuthGroup();
        authGroup.setEmail(userDto.getEmail());
        authGroup.setAuthGroup("USER");
        authGroupRepo.save(authGroup);
        
        return teamMember;
    }
    
    public Users changeEmailPassword(ChangeEmailPasswordDto changeEmailPasswordDto) {
    	Users user = userRepo.findById(changeEmailPasswordDto.getUserId()).orElse(null);
    	user.setEmail(changeEmailPasswordDto.getEmail());
        user.setPassword(passwordEncoder.encode(changeEmailPasswordDto.getPassword()));
        userRepo.save(user);
        return user;
    }
}
