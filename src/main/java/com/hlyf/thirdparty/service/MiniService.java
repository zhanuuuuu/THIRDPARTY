package com.hlyf.thirdparty.service;

import java.util.Map;

/**
 * Created by Administrator on 2019-07-06.
 */
public interface MiniService {

    String GetOpenIdByCode(String code);
    String Wxdecrypt(String encryptedData, String sessionKey, String iv);
    String WxLogin(Map map, String jsonData);
    String WxStoreInfo(Map map, String jsonData);
    String WxStoreGoodsInfo(Map map, String jsonData);
}
