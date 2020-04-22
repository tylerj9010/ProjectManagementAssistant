package dmacc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.TeamMember;
import dmacc.repository.TeamMemberRepository;

//TODO may need to change mapping and return results based on application needs

@Controller
public class TeamMemberController {

	@Autowired
	TeamMemberRepository repo;
	
	@GetMapping({"/viewAllTeamMembers"})
	public String viewAllTeamMembers(Model model) {
		if(repo.findAll().isEmpty()) {
			System.out.println("EMPTY");
			return addNewTeamMember(model);
		}
		model.addAttribute("teamMembers", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputTeamMember")
	public String addNewTeamMember(Model model) {
		TeamMember tm = new TeamMember();
		model.addAttribute("newTeamMember", tm);
		return "input";
	}
	
	@GetMapping("/editTeamMember/{id}")
	public String showTeamMemberToUpdate(@PathVariable("id") long id, Model model) {
		TeamMember tm = repo.findById(id).orElse(null);
		model.addAttribute("newTeamMember", tm);
		return "input";
	}
	
	@PostMapping("/updateTeamMember/{id}")
	public String updateTeamMember(TeamMember tm, Model model) {
		System.out.println("POST");
		repo.save(tm);
		return viewAllTeamMembers(model);
	}
	
	@GetMapping("/deleteTeamMember/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		TeamMember tm = repo.findById(id).orElse(null);
	    repo.delete(tm);
	    return viewAllTeamMembers(model);
	}
}
