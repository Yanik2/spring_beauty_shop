package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.controller.dto.MakeAppointmentDto;
import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Appointment;
import com.yan.beauty_shop_spring2.service.MakeAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeAppointmentServiceImpl implements MakeAppointmentService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public void makeAppointment(MakeAppointmentDto makeAppointmentDto) {
        Optional<Account> masterOpt = accountRepo.findByLogin(makeAppointmentDto.getMasterName());
        UserDetails clientDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> clientOpt = accountRepo.findByLogin(clientDetails.getUsername());
        Account master = masterOpt.get();
        Account client = clientOpt.get();
        Appointment app = new Appointment();
        app.setMasterId(master.getId());
        app.setClientId(client.getId());
        app.setServiceId(master.getService().getId());
        app.setDate(makeAppointmentDto.getDate());
        app.setDone(false);
        app.setPaid(false);
        app.setTimeslotId(makeAppointmentDto.getTimeslotId());
        master.getMasterAppointments().add(app);
        System.out.println(master.getMasterAppointments());
    }
}
