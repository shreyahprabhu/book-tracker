package com.project.bookify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // 👈 This makes your main class a REST controller too
public class BookifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookifyApplication.class, args);
	}
	@GetMapping("/") // 👈 This maps the root URL
	public String home() {
		return "Welcome to Bookify!!";
	}
}



