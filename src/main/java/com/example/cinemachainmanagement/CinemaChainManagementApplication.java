package com.example.cinemachainmanagement;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.enums.ERole;
import com.example.cinemachainmanagement.service.AccountService;
import com.example.cinemachainmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class CinemaChainManagementApplication {
	private final CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(CinemaChainManagementApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Bean
	public void initAdmin(){
		CustomerDTO customerDTO = CustomerDTO
				.builder()
				.email("admin@admin.admin")
				.passHash("admin")
				.role(ERole.ADMIN)
				.build();
		customerService.registerCustomer(customerDTO);
	}
}