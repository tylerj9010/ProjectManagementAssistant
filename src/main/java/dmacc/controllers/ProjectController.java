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
import dmacc.beans.TeamMember;
import dmacc.repository.ProjectRepository;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepo;

	//TODO 
	@GetMapping({"/viewYourProjects/{managerId}"})
	public String viewYourProjects(@PathVariable ("managerId") long id, Model model) {
		return null;
	}
	
	@GetMapping({"/viewAllProjects"})
	public String viewAllProjects(Model model) {
		if(projectRepo.findAll().isEmpty()) {
			return addNewProject(model);
		}
		model.addAttribute("projects", projectRepo.findAll());
		return "projectResults";
	}
	
	@GetMapping("/inputProject")
	public String addNewProject(Model model) {
		TeamMember tm = new TeamMember();
		model.addAttribute("newTeamMember", tm);
		return "input";
	}
	
	@PostMapping("/inputProject/{projectId}")
	public String addNewProject(@PathVariable("projectId") long id, @Valid @ModelAttribute Project p, Model model) {
		projectRepo.save(p);
		return viewAllProjects(model);
	}
	
	@GetMapping("/editProject/{projectId}")
	public String showProjectToUpdate(@PathVariable("projectId") long id, Model model) {
		Project p = projectRepo.findById(id).orElse(null);
		model.addAttribute("newProject", p);
		return "projectResults";
	}
	
	@GetMapping("/deleteProject/{projectId}")
	public String deleteProject(@PathVariable("projectId") long id, Model model) {
		Project p = projectRepo.findById(id).orElse(null);
		projectRepo.delete(p);
		return viewAllProjects(model);
	}
	
}
