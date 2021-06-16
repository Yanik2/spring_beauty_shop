package com.yan.beauty_shop_spring2.service.defaultimpl;

import com.yan.beauty_shop_spring2.controller.dto.GetCatalogRequestDto;
import com.yan.beauty_shop_spring2.dao.CatalogRepo;
import com.yan.beauty_shop_spring2.entity.Account;
import com.yan.beauty_shop_spring2.entity.Role;
import com.yan.beauty_shop_spring2.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.yan.beauty_shop_spring2.constants.Constants.*;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;

    public List<Account> getAllAccounts(GetCatalogRequestDto params) {
        List<Account> accs = catalogRepo.findByRole(Role.MASTER);
        filter(accs, params.getFilterMethod(), params.getFilter());
        sort(accs, params.getSortMethod());
        return accs;

    }

    private void sort(List<Account> catalog, String sortMethod) {
        if (sortMethod.equals(SORT_BY_NAME)) {
            sortByName(catalog);
        } else {
            sortByRate(catalog);
        }
    }

    private void filter(List<Account> catalog, String filterMethod, String filter) {
        if (filterMethod == null) return;
        Iterator<Account> it = catalog.iterator();
        if(filterMethod.equals(FILTER_BY_MASTER)) {
            filterByMaster(it, filter);
        } else {
            filterByService(it, filter);
        }
    }

    private void sortByName(List<Account> catalog) {
        catalog.sort((catalogItem, t1) -> {
            String name = catalogItem.getLogin();
            String name2 = t1.getLogin();
            return name.compareTo(name2);
        });
    }

    private void sortByRate(List<Account> catalog) {
        catalog.sort(Comparator.comparingDouble(Account::getRate));
    }

    private void filterByMaster(Iterator<Account> it, String filter) {
        while(it.hasNext()) {
            Account item = it.next();
            if (!(item.getLogin().equals(filter))) {
                it.remove();
            }
        }
    }

    private void filterByService(Iterator<Account> it, String filter) {
        while (it.hasNext()) {
            Account item = it.next();
            if (!(item.getService().getName().equals(filter))) {
                it.remove();
            }
        }
    }
}
