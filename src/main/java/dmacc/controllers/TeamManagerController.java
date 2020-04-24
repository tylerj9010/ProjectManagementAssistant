package dmacc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.TeamManager;
import dmacc.repository.TeamManagerRepository;

//TODO may need to change mapping and return results based on application needs
//FIXME check how this fits into authentication not sure if we need to have the ability to create a team manager
//		or if that should be done automatically w/ the creation of a new user 

public class TeamManagerController {

	@Autowired
	TeamManagerRepository repo;
	
	@GetMapping({"/viewAllTeamManagers"})
	public String viewAllTeamManagers(Model model) {
		if(repo.findAll().isEmpty()) {
			return addNewTeamManager(model);
		}
		model.addAttribute("teamManagers", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputTeamManager")
	public String addNewTeamManager(Model model) {
		TeamManager tm = new TeamManager();
		model.addAttribute("newTeamManager", tm);
		return "input";
	}
	
	@PostMapping("/inputTeamManager")
	public String addNewTeamManager(@ModelAttribute TeamManager tm, Model model) {
		repo.save(tm);
		return viewAllTeamManagers(model);
	}
	
	@GetMapping("/editTeamManager/{memberId}")
	public String showTeamManagerToUpdate(@PathVariable("memberId") long id, Model model) {
		System.out.println(id);
		TeamManager tm = repo.findById(id).orElse(null);
		model.addAttribute("newTeamManager", tm);
		System.out.println(tm.getManagerId());
		return "input";
	}
	
	@PostMapping("/updateTeamManager/{memberId}")
	public String updateTeamManager(@PathVariable("memberId") long id, @Valid TeamManager tm, Model model) {
		System.out.println(id);
		System.out.println(tm.getManagerId());
		repo.save(tm);
		return viewAllTeamManagers(model);
	}
	
	@GetMapping("/deleteTeamManager/{memberId}")
	public String deleteUser(@PathVariable("memberId") long id, Model model) {
		TeamManager tm = repo.findById(id).orElse(null);
	    repo.delete(tm);
	    return viewAllTeamManagers(model);
	}
}
