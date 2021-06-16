package com.yan.beauty_shop_spring2.dto;

import com.yan.beauty_shop_spring2.entity.Timeslot;
import lombok.Data;

@Data
public class MasterTimeslotDto {
    private Timeslot timeslot;
    private Boolean done;
    private Boolean booked;
}
