package com.yan.beauty_shop_spring2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "timeslot")
@Data
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time")
    private String time;
}
