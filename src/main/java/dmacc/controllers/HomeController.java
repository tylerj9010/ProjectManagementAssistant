package dmacc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dmacc.beans.TeamManager;
import dmacc.beans.Users;
import dmacc.beans.UserDto;
import dmacc.repository.AuthGroupRepository;
import dmacc.repository.TeamManagerRepository;
import dmacc.repository.TeamMemberRepository;
import dmacc.repository.UserRepository;
import dmacc.service.IUserService;
import dmacc.service.UserAlreadyExistException;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private TeamManagerRepository tManagerRepo;
	
	@Autowired
	private TeamMemberRepository tMemberRepo;
	
	@GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){
        return "login.html";
    }
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@GetMapping("/projectdashboard")
	public String getProjectDashboardPage(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName();
		model.addAttribute("teammanager", tManagerRepo.findByUser(userService.findByEmail(email)));
		return "projectdashboard.html";
	}
	
	@GetMapping("/logout")
	public String logoutPage() {
		return "logout.html";
	}
	
	@ModelAttribute("Users")
    public UserDto userDto() {
        return new UserDto();
    }
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
	    return "registration.html";
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

        userService.registerNewUserAccount(userDto);
        return "redirect:/registration?success";
	}
	
	@GetMapping("/addteammember")
	public String showAddTeamMemberForm(Model model) {
	    return "addteammember.html";
	}
	
	@PostMapping("/addteammember")
	public String addTeamMemberAccount(@ModelAttribute("Users") @Valid UserDto userDto, BindingResult result, Model model) {
		
		 Users existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "addteammember";
        }

        userService.registerNewUserAccount(userDto);
        model.addAttribute("teammembers", tMemberRepo.findAll());
        return "projectdashboard.html";
	}
}
