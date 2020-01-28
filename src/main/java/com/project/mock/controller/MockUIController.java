package com.project.mock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MockUIController {

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

	@RequestMapping("/request-response-template")
	public String loginPage() {
		return "request-response-template";
	}

}
