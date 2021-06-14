package com.yan.beauty_shop_spring2.service;

import com.yan.beauty_shop_spring2.controller.dto.GetCatalogRequestDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Service;

import java.util.*;

public interface CatalogService {
    List<Account> getAllAccounts(GetCatalogRequestDto params);
}
