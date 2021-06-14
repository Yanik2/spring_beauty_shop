package com.yan.beauty_shop_spring2.controller;

import com.yan.beauty_shop_spring2.controller.dto.GetCatalogRequestDto;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Service;
import com.yan.beauty_shop_spring2.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import java.util.*;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/")
    public String mainMethod() {
        return "index";
    }

    @GetMapping("/serviceCatalog")
    public String getServiceCatalog(GetCatalogRequestDto params, Model model) {
        List<Account> catalog = catalogService.getAllAccounts(params);
        model.addAttribute("catalog", catalog);
        return "service";
    }
}
