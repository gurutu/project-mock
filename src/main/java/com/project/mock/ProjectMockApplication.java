package com.project.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SpringBootApplication
@Controller
public class ProjectMockApplication {


//	@GetMapping("/")
//	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//		model.addAttribute("name", name);
//		return "home";
//	}
@RequestMapping("/")
public String welcome() {
	return "home";
}

	@RequestMapping("/api-dashboard")
	public String apiDashboard() {
		return "api-dashboard";
	}

	@RequestMapping("/api-details")
	public String apiDetails() {
		return "api-details";
	}

	@RequestMapping("/change-api-details")
	public String changeApiDetails() {
		return "change-api-details";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}


	public static void main(String[] args) {
		SpringApplication.run(ProjectMockApplication.class, args);
	}

}
