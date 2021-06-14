package com.yan.beauty_shop_spring2.security;

import com.yan.beauty_shop_spring2.dao.AccountRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account acc = accountRepo.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return SecurityAccount.fromAccount(acc);
    }
}
