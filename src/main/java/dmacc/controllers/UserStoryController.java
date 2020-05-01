package dmacc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.UserStory;
import dmacc.repository.UserStoryRepository;

public class UserStoryController {

	@Autowired
	UserStoryRepository repo;
		//only for testing should be removed
		//will add method to view tasks for current project once authentication is resolved
		@GetMapping({"/viewAllUserStories"})
		public String viewAllUserStories(Model model) {
			if(repo.findAll().isEmpty()) {
				return addNewUserStory(model);
			}
			model.addAttribute("userStories", repo.findAll());
			return "results";
		}
		
		@GetMapping("/inputUserStory")
		public String addNewUserStory(Model model) {
			UserStory t = new UserStory();
			model.addAttribute("newUserStory", t);
			return "input";
		}
		
		@PostMapping("/inputStory")
		public String addNewUserStory(@ModelAttribute UserStory us, Model model) {
			repo.save(us);
			return viewAllUserStories(model);
		}
		
		@GetMapping("/editUserStory/{storyId}")
		public String showUserStoryToUpdate(@PathVariable("storyId") long id, Model model) {
			UserStory us = repo.findById(id).orElse(null);
			model.addAttribute("newUserStory", us);
			return "input";
		}
		
		@PostMapping("/updateUserStory/{storyId}")
		public String updateUserStory(@PathVariable("storyId") long id, @Valid UserStory us, Model model) {
			repo.save(us);
			return viewAllUserStories(model);
		}
		
		@GetMapping("/deleteUserStory/{storyId}")
		public String deleteUserStory(@PathVariable("storyId") long id, Model model) {
			UserStory us = repo.findById(id).orElse(null);
		    repo.delete(us);
		    return viewAllUserStories(model);
		}
}
