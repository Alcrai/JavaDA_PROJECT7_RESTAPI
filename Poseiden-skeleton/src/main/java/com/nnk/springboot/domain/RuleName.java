package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "json")
    private String json;
    @Column(name = "template")
    private String template;
    @Column(name = "sqlStr")
    private String sqlStr;
    @Column(name = "sqlPart")
    private String sqlPart;


}
