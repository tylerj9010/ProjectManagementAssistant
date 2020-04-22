package dmacc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import dmacc.repository.TaskBacklogRepository;

@Controller
public class TaskBacklogController {
	//TODO add relationship to project table
	
	@Autowired
	TaskBacklogRepository repo;
	
//	@GetMapping("projects/{projectId}/viewTasks") 
//	public String viewAllTasksByProjectId(Model model, @PathVariable (value = "projectId") Long projectId) {
//		if(repo.findByProjectId(projectId).isEmpty()) {
//			return addNewTask(model);
//		}
//		model.addAttribute("tasks", repo.findByProjectId(projectId));
//		return "tasks";
//	}
	
	public String addNewTask(Model model) {
		return null;
	}
	
	public String showTaskToEdit() {
		return null;
	}
	
}
