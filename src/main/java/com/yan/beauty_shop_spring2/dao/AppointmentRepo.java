package com.yan.beauty_shop_spring2.dao;

import com.yan.beauty_shop_spring2.entity.Appointment;
import com.yan.beauty_shop_spring2.entity.AppointmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, AppointmentId> {
    List<Appointment> findAllByOrderByAppointmentIdDateDescAppointmentIdTimeslotTimeDesc();
    Appointment findByAppointmentIdMasterIdAndAppointmentIdDateAndAppointmentIdTimeslotId(Integer masterId, String date, Integer timeslotId);
}
