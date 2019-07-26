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

    String getpushS(HttpServletRequest request,String appSecret,String sig,String urlType,String app_id) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String poisaveS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String CreateShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String AddVirtualShopGoodsPriceRuleS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String EditVirtualShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String DeleteVirtualShopGoodsPriceRuleS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String seltempShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String GetVirtualShopGoodsPriceRulesS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String GetVirtualShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String SetShopStatusS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
    String poicloseS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String poiStoreS(Map map,String data,String url,String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
}
