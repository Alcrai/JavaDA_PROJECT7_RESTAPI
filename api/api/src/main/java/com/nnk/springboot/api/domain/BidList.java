package com.nnk.springboot.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "Bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "BidListId")
    private int bidListId;
    @Column(name = "account")
    private String account;
    @Column(name = "type")
    private String type;

    @Column(name = "bidQuantity")
    private double bidQuantity;

    @Column(name = "askQuantity")
    private double askQuantity;

    @Column(name = "bid")
    private double bid;

    @Column(name = "ask")
    private double ask;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "book")
    private String book;

    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    @Column(name = "dealName")
    private String dealName;

    @Column(name = "dealType")
    private String dealType;

    @Column(name = "sourceListId")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public BidList() {
    }

    public BidList(int bidListId, String account, String type, double bidQuantity) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
