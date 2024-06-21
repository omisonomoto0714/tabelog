package com.example.nagoyamesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);

}
