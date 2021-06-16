package com.yan.beauty_shop_spring2.controller;

import com.yan.beauty_shop_spring2.controller.dto.AppointmentIdDto;
import com.yan.beauty_shop_spring2.controller.dto.MakeAppointmentDto;
import com.yan.beauty_shop_spring2.dto.MasterTimeslotDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.List;

import static com.yan.beauty_shop_spring2.constants.Constants.*;

@Controller
@RequestMapping("/homepage")
public class HomeController {
    @Autowired
    private HomepageService homepageService;
    @Autowired
    private TimeslotService timeslotService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> map = homepageService.getHomepage(userDetails);
        Account acc = (Account) map.get(USER);
        model.addAttribute("table", map.get("table"));
        return HOMEPAGE + acc.getRole().name().toLowerCase() + "home";
    }

    @PostMapping("/timeslots")
    public String getTimeslots(@RequestParam String masterName, @RequestParam String date, Model model) {
        Map<String, Object> map = timeslotService.getTimeslots(masterName, date);
        model.addAttribute(TIMESLOTS, map.get(TIMESLOTS));
        model.addAttribute(MASTERNAME, map.get(MASTERNAME));
        model.addAttribute(DATE, map.get(DATE));
        return HOMEPAGE + CLIENT_TIMESLOT;
    }

    @PostMapping("/makeAppointment")
    public String makeAppointment(MakeAppointmentDto makeAppointmentDto) {
        appointmentService.makeAppointment(makeAppointmentDto);
        return REDIRECT_HOMEPAGE;
    }

    @PostMapping("/markAsDone")
    public String markAsDone(@RequestParam Integer timeslotId) {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        appointmentService.markAsDoneApp(userDetails, timeslotId);
        return REDIRECT_HOMEPAGE;
    }

    @PostMapping("/masterTimeslots")
    public String getMasterTimeslots(AppointmentIdDto appIdDto, HttpSession session, Model model) {
        session.setAttribute("appIdDto", appIdDto);
        List<MasterTimeslotDto> masterTimeslotDtos = appointmentService.getTimeslots(appIdDto.getMasterId(), appIdDto.getDate());
        model.addAttribute(TIMESLOTS, masterTimeslotDtos);
        return HOMEPAGE + ADMIN_TIMESLOT;
    }

    @PostMapping("/changeTime")
    public String changeTime(@RequestParam Integer newTimeslot, HttpSession session) {
        var appointmentIdDto = (AppointmentIdDto) session.getAttribute("appIdDto");
        appointmentService.changeTime(appointmentIdDto, newTimeslot);
        return REDIRECT_HOMEPAGE;
    }

    @PostMapping("/cancelAppointment")
    public String cancelAppointment(AppointmentIdDto appIdDto) {
        appointmentService.cancelAppointment(appIdDto);
        return REDIRECT_HOMEPAGE;
    }

    @PostMapping("/confirmPayment")
    public String confirmPayment(AppointmentIdDto appIdDto){
        appointmentService.confirmPayment(appIdDto);
        return REDIRECT_HOMEPAGE;
    }
}
