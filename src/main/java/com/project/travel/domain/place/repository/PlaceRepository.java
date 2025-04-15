package com.project.travel.domain.place.repository;

import com.project.travel.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
