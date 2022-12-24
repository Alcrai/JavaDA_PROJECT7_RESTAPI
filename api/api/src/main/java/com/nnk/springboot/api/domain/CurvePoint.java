package com.nnk.springboot.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;
    @Column(name="curveId")
    private int curveId;
    @Column(name="asOfDate")
    private Timestamp asOfDate;
    @Column(name="term")
    private double term;
    @Column(name="valeur")
    private double value;
    @Column(name="creationDate")
    private Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint() {
    }
}
