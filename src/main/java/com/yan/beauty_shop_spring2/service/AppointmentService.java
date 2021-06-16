package com.yan.beauty_shop_spring2.service;

import com.yan.beauty_shop_spring2.controller.dto.AppointmentIdDto;
import com.yan.beauty_shop_spring2.controller.dto.MakeAppointmentDto;
import com.yan.beauty_shop_spring2.dto.MasterTimeslotDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AppointmentService {
    void cancelAppointment(AppointmentIdDto appointmentIdDto);
    void confirmPayment(AppointmentIdDto appointmentIdDto);
    List<MasterTimeslotDto> getTimeslots(Integer masterId, String date);
    void changeTime(AppointmentIdDto dto, Integer newTimeslot);
    void makeAppointment(MakeAppointmentDto makeAppointmentDto);
    void markAsDoneApp(UserDetails userDetails, Integer timeslotId);
}
