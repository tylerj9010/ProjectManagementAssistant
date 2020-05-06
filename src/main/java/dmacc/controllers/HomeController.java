package dmacc.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Users;
import dmacc.beans.ChangeEmailPasswordDto;
import dmacc.beans.TeamManager;
import dmacc.beans.TeamMember;
import dmacc.beans.UserDto;
import dmacc.repository.ProjectRepository;
import dmacc.repository.TeamManagerRepository;
import dmacc.repository.TeamMemberRepository;
import dmacc.repository.UserRepository;
import dmacc.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TeamManagerRepository tManagerRepo;
	
	@Autowired
	private TeamMemberRepository tMemberRepo;
	
	
	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){
        return "login";
    }
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/logout-success")
	public String logoutPage() {
		return "redirect:/login";
	}
	
	@ModelAttribute("Users")
    public UserDto userDto() {
        return new UserDto();
    }
	
    public Object getLoggedInUser() {
    	Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName();
	    TeamManager teamManager = tManagerRepo.findByUser(userService.findByEmail(email));
	    TeamMember teamMember = tMemberRepo.findByUser(userService.findByEmail(email));
	    return (teamManager != null) ? teamManager : teamMember;
    }
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
	    return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("Users") @Valid UserDto userDto, BindingResult result) {
		
		Users existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.registerNewTeamManagerAccount(userDto);
        return "redirect:/registration_conf";
	}
	
	@GetMapping("/registration_conf")
	public String showRegistrationConfForm(Model model) {
	    return "registration_conf";
	}
	
	@GetMapping("/change_team_manager_name/{id}")
	public String showUpdateTeamManagerName(@PathVariable("id") long id, Model model) {
		model.addAttribute("yourAccount", getLoggedInUser());
		TeamManager teamManager = tManagerRepo.findById(id).orElse(null);
		model.addAttribute("TeamManager", teamManager);
		return "change_team_manager_name";
	}
	
	@PostMapping("/updateTeamManagerName")
	public String updateTeamManagerName(@ModelAttribute("TeamManager") @Valid TeamManager teamManager, BindingResult result, Model model) { 
		if (result.hasErrors()) {
			return "change_team_manager_name";
        }
		
		tManagerRepo.save(teamManager);
		
		return "redirect:/projectdashboard";
	}
	
	@GetMapping("/addteammember")
	public String showAddTeamMemberForm(Model model) {
		model.addAttribute("yourAccount", getLoggedInUser());
	    return "addteammember";
	}
	
	@PostMapping("/addteammember")
	public String addTeamMemberAccount(@ModelAttribute("Users") @Valid UserDto userDto, BindingResult result, Model model) {
		
		Users existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return showAddTeamMemberForm(model);
        }

        userService.registerNewTeamMemberAccount(userDto);
        
        return "redirect:/manage_team_members";
	}
	
	@GetMapping("/change_team_member_name/{id}")
	public String showUpdateTeamMemberName(@PathVariable("id") long id, Model model) {
		model.addAttribute("yourAccount", getLoggedInUser());
		TeamMember teamMember = tMemberRepo.findById(id).orElse(null);
		model.addAttribute("TeamMember", teamMember);
		return "change_team_member_name";
	}
	
	@PostMapping("/updateTeamMemberName")
	public String updateTeamMemberName(@ModelAttribute("TeamMember") @Valid TeamMember teamMember, BindingResult result, Model model) { 
		if (result.hasErrors()) {
			return "change_team_member_name";
        }
		
		tMemberRepo.save(teamMember);
		
		return "redirect:/manage_team_members";
	}
	
	@GetMapping("/delete_team_member/{id}")
	public String deleteTeamMember(@PathVariable("id") long id, Model model) {
		model.addAttribute("yourAccount", getLoggedInUser());
		TeamMember teamMember = tMemberRepo.findById(id).orElse(null);
		tMemberRepo.delete(teamMember);
		return "redirect:/manage_team_members";
	}
	
	@GetMapping("/manage_team_members")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getManageTeamMembersPage(Model model) {
	    model.addAttribute("yourAccount", getLoggedInUser());
	    model.addAttribute("team_members", tMemberRepo.findByTeamManager((TeamManager) getLoggedInUser()));
		return "manage_team_members";
	}
	
	@ModelAttribute("User")
    public ChangeEmailPasswordDto changeEmailPasswordDto() {
        return new ChangeEmailPasswordDto();
    }
	
	@GetMapping("/change_email_password/{id}")
	public String showChangeEmailPassword(@ModelAttribute("User") ChangeEmailPasswordDto changeEmailPasswordDto, @PathVariable("id") long id, Model model) {
		model.addAttribute("yourAccount", getLoggedInUser());
		Users user = userRepo.findById(id).orElse(null);
		changeEmailPasswordDto.setUserId(user.getUserId());
		changeEmailPasswordDto.setEmail(user.getEmail());
		return "change_email_password";
	}
	
	@Resource(name="authenticationManager")
    private AuthenticationManager authManager;
	
	@PostMapping("/changeEmailPassword/{id}")
	public String changeEmailPassword(@ModelAttribute("User") @Valid ChangeEmailPasswordDto changeEmailPasswordDto, BindingResult result, @PathVariable("id") long id, Model model) {
		Users user = userRepo.findById(id).orElse(null);
		Users existing = userService.findByEmail(changeEmailPasswordDto.getEmail());
		
        if (existing != null && !user.getEmail().equals(changeEmailPasswordDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        
		if (result.hasErrors()) {
			return showChangeEmailPassword(changeEmailPasswordDto, id, model);
        }
		
		changeEmailPasswordDto.setUserId(id);
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName();
	    Users loggedInUserDetails = userService.findByEmail(email);
	    
	    userService.changeEmailPassword(changeEmailPasswordDto);
	    
		if (loggedInUserDetails.getUserId() == id) {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(changeEmailPasswordDto.getEmail(), changeEmailPasswordDto.getPassword());
		    Authentication auth = authManager.authenticate(authReq);
		     
		    SecurityContext sc = SecurityContextHolder.getContext();
		    sc.setAuthentication(auth);

	   	}
		
		return "redirect:/projectdashboard";
	}
	
	@GetMapping("/projectdashboard")
	public String getProjectDashboardPage(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName();
	    TeamManager teamManager = tManagerRepo.findByUser(userService.findByEmail(email));
	    TeamMember teamMember = tMemberRepo.findByUser(userService.findByEmail(email));
	    model.addAttribute("yourAccount", (teamManager != null) ? teamManager : teamMember);
	    model.addAttribute("team_members", tMemberRepo.findByTeamManager(teamManager));
	    model.addAttribute("projects", (teamManager != null) ? projectRepo.findByTeamManager(teamManager) : projectRepo.findByTeamMember(teamMember));
		return "projectdashboard";
	}
}
