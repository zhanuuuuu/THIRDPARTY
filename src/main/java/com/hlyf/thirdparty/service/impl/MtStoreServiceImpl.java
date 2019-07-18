package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.dao.miniprogram.MtStoreDao;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.hlyf.thirdparty.mertuanoverwrite.URLFactoryByZ;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtStoreService;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-11.
 */
@Slf4j
@Service
public class MtStoreServiceImpl implements MtStoreService {

    @Autowired
    private MtStoreDao mtStoreDao;

    @Autowired
    private meituanDao MtDao;

    @Override
    public String GetStoreInfoS(String callJsonText) {
        try{
            List<StoreInfo> storeInfoList=this.mtStoreDao.GetStoreInfo(callJsonText);
            if(storeInfoList!=null  && storeInfoList.size()>0){
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                GlobalEumn.SUCCESS.getMesssage(),storeInfoList));
            }else {
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("获取门店出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                            GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
        }
    }

    @Override
    public String GetStoreGoodsInfoS(String callJsonText) {
        try{
            List<StoreGoodsInfo> storeInfoList=this.mtStoreDao.GetStoreGoodsInfo(callJsonText);
            if(storeInfoList!=null && storeInfoList.size()>0 ){
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                GlobalEumn.SUCCESS.getMesssage(),storeInfoList));
            }else {
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("获取门店商品出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                            GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
        }
    }

    @Override
    public String SyncShop(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                try{
                    Map preMap=map;
                    String afterMapdata= JSON.toJSONString(preMap);
                    log.info("同步门店 转出来的json格式 看下格式 ： {} ",afterMapdata);
                    Map<String,String> parmsMapTrue=new HashMap<String,String>();
                    parmsMapTrue.put("app_poi_codes",(String)preMap.get("app_poi_codes"));
                    try{
                        //这里可能会发生异常
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMapTrue);
                    }catch (ApiSysException|ApiOpException|UnsupportedEncodingException e){
                        log.info("访问美团出错了 ： {} ",e.getMessage());
                        e.printStackTrace();
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS_ERRORTOW.getCode()+"",
                                        GlobalEumn.SUCCESS_ERRORTOW.getMesssage(),resultString));
                    }

                    resultString=CommonUtilImpl.CommSySn(resultString,afterMapdata,MtDao,preMap);
                }catch (Exception e){
                    log.error("同步门店 调用我们的过程出错了 {}",e.getMessage());
                    return JSONObject.toJSONString(
                            new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                                    GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
                }
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }
}
