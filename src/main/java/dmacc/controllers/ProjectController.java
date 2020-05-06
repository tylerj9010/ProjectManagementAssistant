package dmacc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Project;
import dmacc.beans.TeamManager;

import dmacc.repository.ProjectRepository;
import dmacc.repository.TeamMemberRepository;


@Controller
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;
	@Autowired
	TeamMemberRepository tMemberRepo;
	@Autowired
	HomeController homeController;
	
	
	@GetMapping("/inputProject")
	public String addNewProject(Model model) {
		model.addAttribute("yourAccount", homeController.getLoggedInUser());
		Project p = new Project();
		model.addAttribute("newProject", p);
	    model.addAttribute("team_members", tMemberRepo.findByTeamManager((TeamManager) homeController.getLoggedInUser()));
		return "newproject";
	}
	
	@PostMapping("/inputProject/{projectId}")
	public String addNewProject(@PathVariable("projectId") long id, @Valid @ModelAttribute Project p, Model model) {
		Project existingProject = projectRepo.findById(id).orElse(null);
		if (existingProject != null) {
			p.setDateCreated(existingProject.getDateCreated());
			p.setTeamManager(existingProject.getTeamManager());
		} else {
		    TeamManager teamManager = (TeamManager) homeController.getLoggedInUser();
			p.setTeamManager(teamManager);
		}
		
		projectRepo.save(p);
		
		return "redirect:/projectdashboard";
	}
	
	@GetMapping("/editProject/{projectId}")
	public String showProjectToUpdate(@PathVariable("projectId") long id, Model model) {
		model.addAttribute("yourAccount", homeController.getLoggedInUser());
		Project p = projectRepo.findById(id).orElse(null);
		model.addAttribute("newProject", p);
		TeamManager teamManager = p.getTeamManager();
	    model.addAttribute("team_members", tMemberRepo.findByTeamManager(teamManager));
	    return "newproject";
	}
	
	@GetMapping("/details/{projectId}")
	public String showProject(@PathVariable("projectId") long id, Model model) {
		model.addAttribute("yourAccount", homeController.getLoggedInUser());
		Project p = projectRepo.findById(id).orElse(null);
		model.addAttribute("project", p);
		return "projectdetails";
	}
	
	@GetMapping("/deleteProject/{projectId}")
	public String deleteProject(@PathVariable("projectId") long id, Model model) {
		Project p = projectRepo.findById(id).orElse(null);
		projectRepo.delete(p);
		return "redirect:/projectdashboard";
	}
}
