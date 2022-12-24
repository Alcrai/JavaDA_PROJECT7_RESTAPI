package com.nnk.springboot.api.repositories;

import com.nnk.springboot.api.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
