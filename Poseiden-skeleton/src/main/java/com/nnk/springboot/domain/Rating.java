package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
/**
 * Rating Entity
 *
 */
@Data
@Entity
@Table(name = "Rating")
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

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating() {
    }
}
