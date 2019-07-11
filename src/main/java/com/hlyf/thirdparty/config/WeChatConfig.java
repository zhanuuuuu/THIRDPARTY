package com.hlyf.thirdparty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019-05-10.
 */
@Configuration
public class WeChatConfig {

    @Value("${miniprogram.appid}")
    private String appid;

    @Value("${miniprogram.appsecret}")
    private String appsecret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
