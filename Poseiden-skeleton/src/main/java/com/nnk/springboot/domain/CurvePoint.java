package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;
    @Column(name="CurveId")
    private int curveId;
    @Column(name="asOfDate")
    private Timestamp asOfDate;
    @Column(name="term")
    private double term;
    @Column(name="value")
    private double value;
    @Column(name="creationDate")
    private Timestamp creationDate;
}
