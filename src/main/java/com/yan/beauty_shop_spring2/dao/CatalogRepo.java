package com.yan.beauty_shop_spring2.dao;

import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CatalogRepo extends JpaRepository<Account, Integer> {
    List<Account> findByRole(Role role);
}
