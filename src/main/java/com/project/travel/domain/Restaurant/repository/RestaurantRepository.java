package com.project.travel.domain.Restaurant.repository;

import com.project.travel.domain.Restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
