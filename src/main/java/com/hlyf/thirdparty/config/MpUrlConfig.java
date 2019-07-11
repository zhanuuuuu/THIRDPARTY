package com.hlyf.thirdparty.config;

/**
 * Created by Administrator on 2019-07-06.
 * 微信路径
 */
public interface MpUrlConfig {

    /**
     * auth.code2Session
     本接口应在服务器端调用，详细说明参见服务端API。
     登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。更多使用方法详见 小程序登录。
     */
    String GET_OPENDID= "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

}
