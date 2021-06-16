package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.controller.dto.AppointmentIdDto;
import com.yan.beauty_shop_spring2.controller.dto.MakeAppointmentDto;
import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.dao.AppointmentRepo;
import com.yan.beauty_shop_spring2.dao.TimeslotRepo;
import com.yan.beauty_shop_spring2.dto.MasterTimeslotDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Appointment;
import com.yan.beauty_shop_spring2.entity.AppointmentId;
import com.yan.beauty_shop_spring2.entity.Timeslot;
import com.yan.beauty_shop_spring2.service.AppointmentService;
import com.yan.beauty_shop_spring2.service.StaticMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private TimeslotRepo timeslotRepo;

    @Override
    public void cancelAppointment(AppointmentIdDto appointmentIdDto) {
        Appointment ap = appointmentRepo
                .findByAppointmentIdMasterIdAndAppointmentIdDateAndAppointmentIdTimeslotId(appointmentIdDto.getMasterId(),
                        appointmentIdDto.getDate(), appointmentIdDto.getTimeslotId());
        appointmentRepo.delete(ap);
    }

    @Override
    public void confirmPayment(AppointmentIdDto appointmentIdDto) {
        Appointment ap = appointmentRepo
                .findByAppointmentIdMasterIdAndAppointmentIdDateAndAppointmentIdTimeslotId(appointmentIdDto.getMasterId(),
                        appointmentIdDto.getDate(), appointmentIdDto.getTimeslotId());
        ap.setPaid(true);
        appointmentRepo.saveAndFlush(ap);
    }

    @Override
    public List<MasterTimeslotDto> getTimeslots(Integer masterId, String date) {
        Optional<Account> masterOpt = accountRepo.findById(masterId);
        Account master = null;
        if(masterOpt.isPresent()) {
            master = masterOpt.get();
        }
        List<Timeslot> timeslots = timeslotRepo.findAll();
        return StaticMethods.getMasterTimeslotDtos(master, date, timeslots);
    }

    @Override
    public void changeTime(AppointmentIdDto appIdDto, Integer newTimeslotId) {
        Optional<Account> masterOpt = accountRepo.findById(appIdDto.getMasterId());
        Optional<Timeslot> timeslotOpt = timeslotRepo.findById(newTimeslotId);
        Account master = masterOpt.get();
        Timeslot newTimeslot = timeslotOpt.get();
        List<Appointment> appointments = master.getMasterAppointments();
        Appointment appointment = getAppointment(appointments, appIdDto.getTimeslotId(), appIdDto.getDate());
        appointmentRepo.delete(appointment);
        appointment.getAppointmentId().setTimeslot(newTimeslot);
        appointmentRepo.save(appointment);
    }

    @Override
    public void makeAppointment(MakeAppointmentDto makeAppointmentDto) {
        Optional<Account> masterOpt = accountRepo.findByLogin(makeAppointmentDto.getMasterName());
        UserDetails clientDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> clientOpt = accountRepo.findByLogin(clientDetails.getUsername());
        Account master = masterOpt.get();
        Account client = clientOpt.get();
        Timeslot timeslot = timeslotRepo.getById(makeAppointmentDto.getTimeslotId());
        String date = makeAppointmentDto.getDate();
        AppointmentId appId = new AppointmentId(master, client, master.getService(), timeslot, date);
        Appointment ap = new Appointment(appId, false, false);
        appointmentRepo.saveAndFlush(ap);
    }

    @Override
    public void markAsDoneApp(UserDetails userDetails, Integer timeslotId) {
        String masterName = userDetails.getUsername();
        Optional<Account> masterOpt = accountRepo.findByLogin(masterName);
        Account master = masterOpt.get();
        Appointment ap = appointmentRepo
                .findByAppointmentIdMasterIdAndAppointmentIdDateAndAppointmentIdTimeslotId(master.getId(),
                        LocalDate.now().toString(), timeslotId);
        ap.setDone(true);
        appointmentRepo.saveAndFlush(ap);
    }

    private Appointment getAppointment(List<Appointment> appointments, int timeslotId, String date) {
        List<Appointment> filteredAppointments = appointments.stream()
                .filter(ap -> ap.getAppointmentId().getDate().equals(date) &&
                        ap.getAppointmentId().getTimeslot().getId().equals(timeslotId))
                .collect(Collectors.toList());
        return filteredAppointments.get(0);
    }
}
