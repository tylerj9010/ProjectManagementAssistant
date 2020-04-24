package dmacc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@PostMapping("/inputTeamMember")
	public String addNewTeamMember(@ModelAttribute TeamMember tm, Model model) {
		repo.save(tm);
		return viewAllTeamMembers(model);
	}
	
	@GetMapping("/editTeamMember/{memberId}")
	public String showTeamMemberToUpdate(@PathVariable("memberId") long id, Model model) {
		System.out.println(id);
		TeamMember tm = repo.findById(id).orElse(null);
		model.addAttribute("newTeamMember", tm);
		System.out.println(tm.getMemberId());
		return "input";
	}
	
	@PostMapping("/updateTeamMember/{memberId}")
	public String updateTeamMember(@PathVariable("memberId") long id, @Valid TeamMember tm, Model model) {
		repo.save(tm);
		return viewAllTeamMembers(model);
	}
	
	@GetMapping("/deleteTeamMember/{memberId}")
	public String deleteTeamMember(@PathVariable("memberId") long id, Model model) {
		TeamMember tm = repo.findById(id).orElse(null);
	    repo.delete(tm);
	    return viewAllTeamMembers(model);
	}
}
