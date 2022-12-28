package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @Mock
    private RatingRepository ratingRepository;
    private RatingService ratingService;
    private Rating rating;

    @BeforeEach
    public void initTest(){
        ratingService= new RatingService(ratingRepository);
        rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
    }

    @Test
    public void saveTest(){
        when(ratingRepository.save(rating)).thenReturn(rating);
        assertThat(ratingService.save(rating)).isEqualTo(rating);
        verify(ratingRepository).save(rating);
    }
    @Test
    public void findAllTest(){
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        when (ratingRepository.findAll()).thenReturn(ratingList);
        assertThat(ratingService.findAll()).asList();
        verify(ratingRepository).findAll();
    }
    @Test
    public void deleteTest(){
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        assertThat(ratingService.delete(1)).isEqualTo(rating);
        verify(ratingRepository).findById(1);
    }
    @Test
    public void findByIdTest(){
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        assertThat(ratingService.findById(1)).isEqualTo(rating);
        verify(ratingRepository).findById(1);
    }
    @Test
    public void updatetest(){
        when(ratingRepository.save(rating)).thenReturn(rating);
        assertThat(ratingService.update(rating,1)).isEqualTo(rating);
        verify(ratingRepository).save(rating);
    }
}
