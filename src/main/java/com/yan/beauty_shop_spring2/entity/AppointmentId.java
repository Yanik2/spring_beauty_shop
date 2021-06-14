package com.yan.beauty_shop_spring2.entity;

import java.io.Serializable;

public class AppointmentId implements Serializable {
    private Integer masterId;
    private Integer clientId;
    private Integer serviceId;
    private Integer timeslotId;
    private String date;
}
