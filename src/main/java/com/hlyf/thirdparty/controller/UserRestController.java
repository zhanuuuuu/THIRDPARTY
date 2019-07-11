package com.hlyf.thirdparty.controller;


import com.hlyf.thirdparty.domain.City;
import com.hlyf.thirdparty.domain.User;
import com.hlyf.thirdparty.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
@Slf4j
@ApiIgnore
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "userName", required = true) String userName) {
        log.info("123");
        return userService.findByName(userName);
    }

    @RequestMapping(value = "/api/user2", method = RequestMethod.GET)
    public String  findByName2() {
        log.info("123");
        return userService.findByName2().toString();
    }

    @RequestMapping(value = "/api/Template", method = RequestMethod.GET)
    public String findTemplate() {
        log.info("123");
        return restTemplate.getForObject("http://localhost:15689/Thirdparty/api/user2?userName=泥瓦匠",String.class);
    }

}
