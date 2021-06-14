package com.yan.beauty_shop_spring2.controller;

import com.yan.beauty_shop_spring2.controller.dto.MakeAppointmentDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.service.HomepageService;
import com.yan.beauty_shop_spring2.service.MakeAppointmentService;
import com.yan.beauty_shop_spring2.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.yan.beauty_shop_spring2.constants.Constants.*;

@Controller
@RequestMapping("/homepage")
public class HomeController {
    @Autowired
    private HomepageService homepageService;
    @Autowired
    private TimeslotService timeslotService;
    @Autowired
    private MakeAppointmentService makeAppointmentService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> map = homepageService.getHomepage(userDetails);
        Account acc = (Account) map.get("user");
        model.addAttribute("table", map.get("table"));
        return acc.getRole().name().toLowerCase() + "homepage";
    }

    @PostMapping("/timeslots")
    public String getTimeslots(@RequestParam String masterName, @RequestParam String date, Model model) {
        Map<String, Object> map = timeslotService.getTimeslots(masterName, date);
        model.addAttribute(TIMESLOTS, map.get(TIMESLOTS));
        model.addAttribute(MASTERNAME, map.get(MASTERNAME));
        model.addAttribute(DATE, map.get(DATE));
        return "clienttimeslot";
    }

    @PostMapping("/makeAppointment")
    public String makeAppointment(MakeAppointmentDto makeAppointmentDto) {
        makeAppointmentService.makeAppointment(makeAppointmentDto);
        return "redirect:/homepage/";
    }
}
