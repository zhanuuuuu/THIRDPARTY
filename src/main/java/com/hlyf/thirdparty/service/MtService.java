package com.hlyf.thirdparty.service;



import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2019-06-24.
 */
public interface MtService {

    String transmitS(String appId, String appSecret,
                            String requestMethod,String meituanurl,
                            HttpServletRequest request) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String poistateupdateS(HttpServletRequest request,String appSecret,String sig) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String getpushS(HttpServletRequest request,String appSecret,String sig,String urlType) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String poisaveS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String poiOpenS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String poicloseS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String poiStoreS(Map map,String data,String url,String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
}
