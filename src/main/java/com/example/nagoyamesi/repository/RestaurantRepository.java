package com.example.nagoyamesi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	public Page<Restaurant> findByNameLike(String keyword, Pageable pageable);

}
