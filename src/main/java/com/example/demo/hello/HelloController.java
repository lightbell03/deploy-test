package com.example.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello() {
		return "hello this is test controller";
	}

	@GetMapping("/deploy")
	public String deploy() {
		return "deploy test";
	}
}
