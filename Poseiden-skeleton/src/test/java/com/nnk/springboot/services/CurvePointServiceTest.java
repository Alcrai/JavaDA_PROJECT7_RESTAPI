package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceTest {
    @Mock
    private CurvePointRepository curvePointRepository;
    private CurvePointService curvePointService;
    private CurvePoint curvePoint;

    @BeforeEach
    public void initTest(){
        curvePointService = new CurvePointService(curvePointRepository);
        curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint.setId(1);
    }

    @Test
    public void saveTest(){
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        assertThat(curvePointService.save(curvePoint)).isEqualTo(curvePoint);
        verify(curvePointRepository).save(curvePoint);
    }
    @Test
    public void findAllTest(){
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);
        when(curvePointRepository.findAll()).thenReturn(curvePointList);
        assertThat(curvePointService.findAll()).asList();
        verify(curvePointRepository).findAll();
    }
    @Test
    public void deleteTest(){
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        assertThat(curvePointService.delete(1)).isEqualTo(curvePoint);
        verify(curvePointRepository).findById(1);
    }
    @Test
    public void findByIdTest(){
        when (curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        assertThat(curvePointService.findById(1)).isEqualTo(curvePoint);
        verify(curvePointRepository).findById(1);
    }
    @Test
    public void updateTest(){
        when (curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        assertThat(curvePointService.update(curvePoint,1)).isEqualTo(curvePoint);
        verify(curvePointRepository).save(curvePoint);
    }

}
