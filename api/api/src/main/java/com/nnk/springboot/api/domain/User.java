package com.nnk.springboot.api.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="fullname")
    private String fullname;
    @Column(name="role")
    private String role;


}
