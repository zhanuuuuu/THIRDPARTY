package com.hlyf.thirdparty.service.impl;


import com.hlyf.thirdparty.dao.miniprogram.CityDao;
import com.hlyf.thirdparty.dao.supermarket.UserDao;
import com.hlyf.thirdparty.domain.City;
import com.hlyf.thirdparty.domain.User;
import com.hlyf.thirdparty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("温岭市");
        user.setCity(city);
        return user;
    }

    @Override
    public City findByName2() {

        City city = cityDao.findByName("温岭市");

        return city;
    }
}
