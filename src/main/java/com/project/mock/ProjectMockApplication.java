package com.project.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SpringBootApplication
public class ProjectMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMockApplication.class, args);
	}

}
