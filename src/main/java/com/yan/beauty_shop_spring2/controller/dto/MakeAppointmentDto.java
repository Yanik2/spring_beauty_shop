package com.yan.beauty_shop_spring2.controller.dto;

import com.yan.beauty_shop_spring2.entity.Timeslot;
import lombok.Data;

@Data
public class MakeAppointmentDto {
    private String masterName;
    private String date;
    private Integer timeslotId;
}
