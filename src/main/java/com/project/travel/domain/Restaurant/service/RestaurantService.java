package com.project.travel.domain.Restaurant.service;

import com.project.travel.domain.Restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository placeRepository;
}
