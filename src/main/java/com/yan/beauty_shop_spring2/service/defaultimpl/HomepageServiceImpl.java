package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.dao.CatalogRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Role;
import com.yan.beauty_shop_spring2.service.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class HomepageServiceImpl implements HomepageService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CatalogRepo catalogRepo;

    @Override
    public Map<String, Object> getHomepage(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepo.findByLogin(username);
        Map<String, Object> map = new HashMap<>();
        switch (accountOptional.get().getRole()) {
            case ADMIN:
                break;
            case CLIENT:
                map.put("table", catalogRepo.findByRole(Role.MASTER));
                break;
            case MASTER:
                break;
        }
        map.put("user", accountOptional.get());
        return map;
    }
}
