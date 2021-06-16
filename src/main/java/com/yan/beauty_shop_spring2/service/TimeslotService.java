package com.yan.beauty_shop_spring2.service;

import java.util.Map;

public interface TimeslotService {
    Map<String, Object> getTimeslots(String masterName, String date);
}
