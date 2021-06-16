package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.dao.AppointmentRepo;
import com.yan.beauty_shop_spring2.dao.CatalogRepo;
import com.yan.beauty_shop_spring2.dao.TimeslotRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Role;
import com.yan.beauty_shop_spring2.entity.Timeslot;
import com.yan.beauty_shop_spring2.service.HomepageService;
import com.yan.beauty_shop_spring2.service.StaticMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.yan.beauty_shop_spring2.constants.Constants.*;

@Service
public class HomepageServiceImpl implements HomepageService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private TimeslotRepo timeslotRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public Map<String, Object> getHomepage(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepo.findByLogin(username);
        Account acc = accountOptional.get();
        Map<String, Object> map = new HashMap<>();
        switch (accountOptional.get().getRole()) {
            case ADMIN:
                map.put(TABLE, appointmentRepo.findAllByOrderByAppointmentIdDateDescAppointmentIdTimeslotTimeDesc());
                break;
            case CLIENT:
                map.put(TABLE, catalogRepo.findByRole(Role.MASTER));
                break;
            case MASTER:
                List<Timeslot> timeslots = timeslotRepo.findAll();
                map.put(TABLE, StaticMethods.getMasterTimeslotDtos(acc, LocalDate.now().toString(), timeslots));
                break;
        }
        map.put(USER, acc);
        return map;
    }
}
