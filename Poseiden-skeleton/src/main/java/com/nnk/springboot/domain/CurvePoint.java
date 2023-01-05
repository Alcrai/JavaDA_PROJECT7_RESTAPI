package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
/**
 * CurvePoint Entity
 *
 */
@Data
@Entity
@Table(name = "Curvepoint")
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

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint() {
    }
}
