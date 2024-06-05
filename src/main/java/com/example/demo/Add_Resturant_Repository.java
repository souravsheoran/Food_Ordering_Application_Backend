package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Add_Resturant_Repository extends JpaRepository<Add_Resturant,Long>{


	List<Add_Resturant> findByNameContaining(String Rname);

}
