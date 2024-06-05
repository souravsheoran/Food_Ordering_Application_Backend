package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Registration_Repository extends JpaRepository<User_Registration, Long> {
	
	User_Registration findByEmail(String email);
	
}
