package com.yan.beauty_shop_spring2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Data
@IdClass(AppointmentId.class)
public class Appointment {
    @Id
    private Integer masterId;
    @Id
    private Integer clientId;
    @Id
    private Integer serviceId;
    @Id
    private Integer timeslotId;
    @Id
    private String date;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "done")
    private Boolean done;
}
