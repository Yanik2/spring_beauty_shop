package com.yan.beauty_shop_spring2.service;

import com.yan.beauty_shop_spring2.entity.Timeslot;
import java.util.List;
import java.util.Map;

public interface TimeslotService {
    Map<String, Object> getTimeslots(String masterName, String date);
}
