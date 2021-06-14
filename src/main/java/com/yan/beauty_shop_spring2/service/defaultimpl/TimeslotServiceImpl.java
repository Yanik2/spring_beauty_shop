package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.dao.TimeslotRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Appointment;
import com.yan.beauty_shop_spring2.entity.Timeslot;
import com.yan.beauty_shop_spring2.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.yan.beauty_shop_spring2.constants.Constants.*;

@Service
public class TimeslotServiceImpl implements TimeslotService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TimeslotRepo timeslotRepo;

    @Override
    public Map<String, Object> getTimeslots(String masterName, String date) {
        Optional<Account> masterOpt = accountRepo.findByLogin(masterName);
        List<Timeslot> timeslots = timeslotRepo.findAll();
        List<Appointment> appointments = filterAppointmentsByDate(masterOpt.get().getMasterAppointments(), date);
        List<Timeslot> filteredTimeslot = filterTimeslots(appointments, timeslots);
        Map<String, Object> map = new HashMap<>();
        map.put(TIMESLOTS, filteredTimeslot);
        map.put(MASTERNAME, masterName);
        map.put(DATE, date);
        return map;
    }

    private List<Appointment> filterAppointmentsByDate(List<Appointment> apps, String date) {
        apps = apps.stream().filter(app -> app.getDate().equals(date)).collect(Collectors.toList());
        return apps;
    }

    private List<Timeslot> filterTimeslots(List<Appointment> apps, List<Timeslot> timeslots) {
        for(Appointment a : apps) {
            timeslots = timeslots.stream().filter(timeslot -> !timeslot.getId().equals(a.getTimeslotId()))
            .collect(Collectors.toList());
        }
        System.out.println(timeslots);
        return timeslots;
    }
}
