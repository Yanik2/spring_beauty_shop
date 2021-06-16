package com.yan.beauty_shop_spring2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AppointmentId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Account master;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Account client;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;
    @Column(name = "date")
    private String date;
}
