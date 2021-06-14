package com.yan.beauty_shop_spring2.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface HomepageService {
    Map<String, Object> getHomepage(UserDetails userDetails);
}
