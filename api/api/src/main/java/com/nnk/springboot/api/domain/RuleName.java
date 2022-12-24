package com.nnk.springboot.api.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Rulename")
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

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public RuleName() {
    }
}
