package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/food/ordering")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private User_Registration_Repository userRegistrationRepository;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestParam(name = "name") String name,
                                             @RequestParam(name = "email") String email,
                                             @RequestParam(name = "password") String password,
                                             @RequestParam(name = "confirmPassword") String confirmPassword) {
        logger.info("Received request to create user with email: {}", email);
        try {
            if (!password.equals(confirmPassword)) {
                logger.warn("Password and confirm password do not match");
                return ResponseEntity.badRequest().body("Passwords do not match");
            }

            User_Registration newUser = new User_Registration(name, password, email);
            userRegistrationRepository.save(newUser);

            logger.info("User created successfully with email: {}", email);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            logger.error("Error creating user: ", e);
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
    
   

    
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {

    	User_Registration  existingUser = userRegistrationRepository.findByEmail(email);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
        	System.out.println("User found");
            return ResponseEntity.ok("Login successful!");
        } else {
            System.out.println("User not found");
        	return ResponseEntity.status(401).body("Invalid email or password!");
        }
    }
    
    @Autowired
   private  Add_Resturant_Repository add_Resturant_Repository;
    
    
    
    @PostMapping("/Add_Resturant")
    public ResponseEntity<String> addResturant(@RequestParam(name = "name") String name,
                                              @RequestParam(name = "rating") String rating,
                                              @RequestParam(name = "address") String address,
                                              @RequestParam(name = "description") String description,
                                              @RequestParam(name = "contact") int contact,
                                              @RequestParam(name = "openTime") String openTime,
                                              @RequestParam(name = "closeTime") String closeTime,
                                              @RequestParam(name = "special_feature") String special_feature,
                                              @RequestParam(name = "userId") Long userId) {
        try {
            Optional<User_Registration> userOptional = userRegistrationRepository.findById(userId);

            if (userOptional.isPresent()) {
                User_Registration user = userOptional.get();
                Add_Resturant restaurant = new Add_Resturant(name, rating, address, description, contact, openTime, closeTime, special_feature, user);
                add_Resturant_Repository.save(restaurant);
                return ResponseEntity.ok("Restaurant added successfully");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
    
    // API for getting  all restaurant name using Given Name Only
    
    @GetMapping("/find_Resturant")
    public ResponseEntity<List<Add_Resturant>> findResturantsByName(@RequestParam(name = "name") String name) {
        
            List<Add_Resturant> restaurants = add_Resturant_Repository.findByNameContaining(name);
            if (!restaurants.isEmpty()) {

            	return ResponseEntity.ok(restaurants);
            } else {

            	return ResponseEntity.notFound().build();
            }
       
    }

    	
    }

    
    

    	
    	
    	
    	
    	
    	
    	
    	

    	
    	
    	