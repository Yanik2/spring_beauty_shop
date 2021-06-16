package com.yan.beauty_shop_spring2.controller.dto;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppointmentIdDto implements Serializable {
    private Integer masterId;
    private Integer clientId;
    private Integer serviceId;
    private Integer timeslotId;
    private String date;
}
