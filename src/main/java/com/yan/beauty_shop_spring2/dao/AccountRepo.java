package com.yan.beauty_shop_spring2.dao;

import com.yan.beauty_shop_spring2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByLogin(String login);
}
