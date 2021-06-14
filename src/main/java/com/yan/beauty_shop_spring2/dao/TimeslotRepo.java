package com.yan.beauty_shop_spring2.dao;

import com.yan.beauty_shop_spring2.entity.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepo extends JpaRepository<Timeslot, Integer> {
}
