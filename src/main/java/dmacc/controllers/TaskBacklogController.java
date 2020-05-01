package dmacc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.TaskBacklog;
import dmacc.repository.TaskBacklogRepository;


//TODO may need to change mapping and return results based on application needs

@Controller
public class TaskBacklogController {
	
	@Autowired
	TaskBacklogRepository repo;
	
	//only for testing should be removed
	//will add method to view tasks for current project once authentication is resolved
	@GetMapping({"/viewAllTasks"})
	public String viewAllTasks(Model model) {
		if(repo.findAll().isEmpty()) {
			return addNewTask(model);
		}
		model.addAttribute("tasks", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputTask")
	public String addNewTask(Model model) {
		TaskBacklog t = new TaskBacklog();
		model.addAttribute("newTask", t);
		return "input";
	}
	
	@PostMapping("/inputTask")
	public String addNewTask(@ModelAttribute TaskBacklog t, Model model) {
		repo.save(t);
		return viewAllTasks(model);
	}
	
	@GetMapping("/editTask/{taskId}")
	public String showTaskToUpdate(@PathVariable("taskId") long id, Model model) {
		TaskBacklog t = repo.findById(id).orElse(null);
		model.addAttribute("newTask", t);
		return "input";
	}
	
	@PostMapping("/updateTask/{taskId}")
	public String updateTeamManager(@PathVariable("taskId") long id, @Valid TaskBacklog t, Model model) {
		repo.save(t);
		return viewAllTasks(model);
	}
	
	@GetMapping("/deleteTask/{taskId}")
	public String deleteTask(@PathVariable("taskId") long id, Model model) {
		TaskBacklog t = repo.findById(id).orElse(null);
	    repo.delete(t);
	    return viewAllTasks(model);
	}
	
}
