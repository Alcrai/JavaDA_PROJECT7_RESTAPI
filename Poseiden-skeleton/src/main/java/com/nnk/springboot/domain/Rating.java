package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private int id;
    @Column(name = "moodysRating")
    private String moodysRating;
    @Column(name = "sandPRating")
    private String sandPRating;
    @Column(name = "fitchRating")
    private String fitchRating;
    @Column(name = "orderNumber")
    private int orderNumber;
}
