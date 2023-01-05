package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RateControllerTest {
    @Mock
    private RatingService ratingService;
    private RatingController ratingController;
    private Rating rating;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void initTest(){
        ratingController =new RatingController(ratingService);
        rating =new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
    }

    @Test
    public void homeTest(){
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        when(ratingService.findAll()).thenReturn(ratingList);
        assertThat(ratingController.home(model)).isEqualTo("rating/list");
        verify(ratingService).findAll();
    }
    @Test
    public void addRatingFormTest(){
        assertThat(ratingController.addRatingForm(rating,"","","",model)).isEqualTo("rating/add");
    }
    @Test
    public void validateTest(){
        Rating ratingNull = new Rating("","","",10);
        assertThat(ratingController.validate(ratingNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/rating/add");
        assertThat(ratingController.validate(rating,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("rating/add");
    }
    @Test
    public void showUpdateFormTest(){
        when(ratingService.findById(1)).thenReturn(rating);
        assertThat(ratingController.showUpdateForm(1,"","","",model)).isEqualTo("rating/update");
        verify(ratingService).findById(1);
    }
    @Test
    public void updateCurveTest(){
        Rating ratingNull = new Rating("","","",10);
        assertThat(ratingController.updateRating(1,ratingNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/rating/update/{id}");
        assertThat(ratingController.updateRating(1,rating,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/rating/update/1");
    }
    @Test
    public void deleteCurvetest(){
        when(ratingService.delete(1)).thenReturn(rating);
        assertThat(ratingController.deleteRating(1,model)).isEqualTo("redirect:/rating/list");
        verify(ratingService).delete(1);
    }

}
