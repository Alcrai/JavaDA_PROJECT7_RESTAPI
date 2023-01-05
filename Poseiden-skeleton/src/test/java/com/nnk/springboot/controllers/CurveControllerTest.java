package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveControllerTest {
    @Mock
    private CurvePointService curvePointService;
    private CurveController curveController;
    private CurvePoint curvePoint;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void initTest(){
        curveController =new CurveController(curvePointService);
        curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint.setId(1);
    }

    @Test
    public void homeTest(){
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);
        when(curvePointService.findAll()).thenReturn(curvePointList);
        assertThat(curveController.home(model)).isEqualTo("curvePoint/list");
        verify(curvePointService).findAll();
    }
    @Test
    public void addCurveFormTest(){
        assertThat(curveController.addCurveForm(curvePoint,"",model)).isEqualTo("curvePoint/add");
    }
    @Test
    public void validateTest(){
        CurvePoint curveNull = new CurvePoint(0, 0, 30d);
        assertThat(curveController.validate(curveNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/curvePoint/add");
        assertThat(curveController.validate(curvePoint,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("curvePoint/add");
    }
    @Test
    public void showUpdateFormTest(){
        when(curvePointService.findById(1)).thenReturn(curvePoint);
        assertThat(curveController.showUpdateForm(1,"",model)).isEqualTo("curvePoint/update");
        verify(curvePointService).findById(1);
    }
    @Test
    public void updateCurveTest(){
        CurvePoint curveNull = new CurvePoint(0, 0, 30d);
        assertThat(curveController.updateCurve(1,curveNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/curvePoint/update/{id}");
        assertThat(curveController.updateCurve(1,curvePoint,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/curvePoint/update/1");
    }
    @Test
    public void deleteCurvetest(){
        when(curvePointService.delete(1)).thenReturn(curvePoint);
        assertThat(curveController.deleteCurve(1,model)).isEqualTo("redirect:/curvePoint/list");
        verify(curvePointService).delete(1);
    }

}
