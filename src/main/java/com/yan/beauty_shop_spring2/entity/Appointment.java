package com.yan.beauty_shop_spring2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @EmbeddedId
    private AppointmentId appointmentId;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "done")
    private Boolean done;
}
