package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.config.MpUrlConfig;
import com.hlyf.thirdparty.config.WeChatConfig;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.LoginResult;
import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MiniService;
import com.hlyf.thirdparty.tool.AesCbcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-06.
 */
@Service
@Slf4j
public class MiniServiceImpl implements MiniService,MpUrlConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private meituanDao MtDao;

    @Autowired
    private WeChatConfig weChatConfig;

    @Override
    public String GetOpenIdByCode(String code) {
        String resultString="";
        try{
            resultString=restTemplate.getForObject(
                    String.format(GET_OPENDID,
                            weChatConfig.getAppid(),weChatConfig.getAppsecret(),code),
                    String.class);
            resultString= JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",GlobalEumn.SUCCESS.getMesssage(),resultString));
        }catch (Exception e){
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 {}",e.getMessage());
            e.printStackTrace();
            resultString= JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.MINIPROGRAM_ERROR.getCode()+"",GlobalEumn.MINIPROGRAM_ERROR.getMesssage(),resultString));
        }

        return resultString;
    }

    @Override
    public String Wxdecrypt(String encryptedData, String sessionKey, String iv) {
        String resultString ="";
        try {
             resultString = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (null != resultString && resultString.length() > 0) {
                resultString= JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",GlobalEumn.SUCCESS.getMesssage(),resultString));
            } else {
                resultString= JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.MINIPROGRAM_DECRYPT.getCode()+"",GlobalEumn.MINIPROGRAM_DECRYPT.getMesssage(),resultString));
            }
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 {}",e.getMessage());
            e.printStackTrace();
            resultString= JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.MINIPROGRAM_DECRYPT.getCode()+"",GlobalEumn.MINIPROGRAM_DECRYPT.getMesssage(),resultString));
        }
        return resultString;
    }

    @Override
    public String WxLogin(Map map, String jsonData) {
        String resultString="";
        try{
            List<LoginResult> loginResultList=this.MtDao.ExecProceLogin(jsonData);
            if(loginResultList!=null && loginResultList.size()>0 && loginResultList.get(0).getResult().equals("1")){
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.SUCCESS.getCode(),
                                GlobalEumn.SUCCESS.getMesssage(), JSON.toJSONString(loginResultList)));
            }else {
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getCode(),
                                GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getMesssage(), ""));
            }
        }catch (Exception e){
            log.error("WxLogin 登录出错了 {}",e.getMessage());

            return JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getCode(),
                            GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getMesssage(), ""));
        }

        return resultString;
    }

    @Override
    public String WxStoreInfo(Map map, String jsonData) {
        String resultString="";
        try{
            List<StoreInfo> loginResultList=this.MtDao.GetStoreInfo(jsonData);
            if(loginResultList!=null && loginResultList.size()>0 && loginResultList.get(0).getResult().equals("1")){
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.SUCCESS.getCode(),
                                GlobalEumn.SUCCESS.getMesssage(), JSON.toJSONString(loginResultList)));
            }else {
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_EMPTY.getCode(),
                                GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(), ""));
            }
        }catch (Exception e){
            log.error("WxStoreInfo 出错了 {}",e.getMessage());

            return JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.SYSTEM_ERROR.getCode(),
                            GlobalEumn.SYSTEM_ERROR.getMesssage(), ""));
        }

        return resultString;
    }

    @Override
    public String WxStoreGoodsInfo(Map map, String jsonData) {
        String resultString="";
        try{
            List<StoreGoodsInfo> loginResultList=this.MtDao.GetStoreGoodsInfo(jsonData);
            if(loginResultList!=null && loginResultList.size()>0 && loginResultList.get(0).getResult().equals("1")){
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.SUCCESS.getCode(),
                                GlobalEumn.SUCCESS.getMesssage(), JSON.toJSONString(loginResultList)));
            }else {
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_EMPTY.getCode(),
                                GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(), ""));
            }
        }catch (Exception e){
            log.error("StoreGoodsInfo 出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.SYSTEM_ERROR.getCode(),
                            GlobalEumn.SYSTEM_ERROR.getMesssage(), ""));
        }

        return resultString;
    }
}
