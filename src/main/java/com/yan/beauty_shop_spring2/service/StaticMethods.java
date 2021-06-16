package com.yan.beauty_shop_spring2.service;

import com.yan.beauty_shop_spring2.dto.MasterTimeslotDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Appointment;
import com.yan.beauty_shop_spring2.entity.Timeslot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaticMethods {

    public final static List<Appointment> filterAppointmentsByDate(List<Appointment> apps, String date) {
        apps = apps.stream().filter(app -> app.getAppointmentId().getDate().equals(date)).collect(Collectors.toList());
        return apps;
    }

    public static List<MasterTimeslotDto> getMasterTimeslotDtos(Account acc, String date, List<Timeslot> timeslots) {
        List<Appointment> filteredApps = filterAppointmentsByDate(acc.getMasterAppointments(), date);
        List<MasterTimeslotDto> masterTimeslotDtos = getDtoList(filteredApps, timeslots);
        return masterTimeslotDtos;
    }

    private static List<MasterTimeslotDto> getDtoList(List<Appointment> apps, List<Timeslot> timeslots) {
        List<MasterTimeslotDto> masterTimeslotDtos = new ArrayList<>();
        for(Timeslot t : timeslots) {
            MasterTimeslotDto dto = new MasterTimeslotDto();
            dto.setTimeslot(t);
            for(Appointment a : apps) {
                if(a.getAppointmentId().getTimeslot().getId() == t.getId()) {
                    dto.setDone(a.getDone());
                    dto.setBooked(true);
                }
            }
            masterTimeslotDtos.add(dto);
        }
        return masterTimeslotDtos;
    }
}
