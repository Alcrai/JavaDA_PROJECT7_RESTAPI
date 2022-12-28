package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Optional<Rating> findById(Integer id) {
        return ratingRepository.findById(id);
    }

    public Rating update(Rating rating, Integer id) {
        rating.setId(id);
        ratingRepository.save(rating);
        return rating;
    }

    public Rating delete(Integer id) {
        Rating rating = ratingRepository.findById(id).get();
        ratingRepository.delete(rating);
        return rating;
    }
}
