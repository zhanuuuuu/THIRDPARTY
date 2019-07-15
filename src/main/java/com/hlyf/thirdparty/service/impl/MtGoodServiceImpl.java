package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.config.MtpushConfig;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.RepResult;
import com.hlyf.thirdparty.mertuanoverwrite.URLFactoryByZ;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtGoodService;
import com.hlyf.thirdparty.tool.MapRemoveNullUtil;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2019-07-06.
 */
@Service
@Slf4j
public class MtGoodServiceImpl implements MtGoodService,MtpushConfig {

    @Autowired
    private meituanDao MtDao;

    @Override
    public String GoodsUpdateS(Map map,String data,String url,String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                Map parmsMap = CommonUtilImpl.clearMap(map);
                resultString = URLFactoryByZ.requestApi(method,
                        url, systemParamsMap, parmsMap);
                resultString=CommonUtilImpl.MyExecProce(resultString,data,MtDao);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }

    /**
     *   <pre>
     *       商超便利商品类APIretail/sku/sellStatus 批量更新售卖状态
     *   </pre>
     * @param map
     * @param data
     * @param url
     * @param method
     * @return
     * @throws ApiSysException
     * @throws ApiOpException
     * @throws UnsupportedEncodingException
     */
    @Override
    public String GoodsSkusellStatusS(Map map,String data,String url,String method) throws ApiSysException,
                                                                                    ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                String sqltextback=(String) map.get("sqltextback");
                try{
                    RepResult repResult=this.MtDao.GoodsSkusellStatusD(data);
                    if(repResult!=null && repResult.getResult().equals("1")){

                        Map preMap=map;
                        preMap.put("sqltext",sqltextback);

                        String afterMapdata= JSON.toJSONString(preMap);
                        log.info("商品上架 转出来的json格式 看下格式 ： {} ",afterMapdata);
                        //这里符合我们上架规则才可以上架
                        map.remove("virtualshopid");
                        map.remove("phone");
                        map.remove("goodsId");
                        map.remove("sqltextback");
                        Map parmsMap = CommonUtilImpl.clearMap(map);
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMap);
                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);

                    }else {
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_GOODONLINE.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_GOODONLINE.getMesssage(),""));
                    }
//
//                    Map preMap=map;
//                    preMap.put("sqltext",sqltextback);
//
//                    String afterMapdata= JSON.toJSONString(preMap);
//                    log.info("商品上架 转出来的json格式 看下格式 ： {} ",afterMapdata);
//                    //这里符合我们上架规则才可以上架
//                    map.remove("virtualshopid");
//                    map.remove("phone");
//                    map.remove("goodsId");
//                    map.remove("sqltextback");
//                    Map parmsMap = CommonUtilImpl.clearMap(map);
//                    Map<String,String> new_map_String = new HashMap();
//                    for(Object key:parmsMap.keySet()){
//                        new_map_String.put(key+"", String.valueOf(parmsMap.get(key)));
//                        System.out.println(key+" : "+String.valueOf(parmsMap.get(key)));
//                    }
//
//                    String afterMapdata1= JSON.toJSONString(new_map_String);
//                    log.info("上传的参数美团 看下格式 ： {} ",afterMapdata1);

//                    resultString = URLFactoryByZ.requestApi(method,
//                            url, systemParamsMap, new_map_String);
//                    resultString=CommonUtilImpl.checkGoodsSkusellStatus(resultString,afterMapdata,MtDao);
                }catch (Exception e){
                    log.error("商品上架 调用我们的过程出错了 {}",e.getMessage());
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

    @Override
    public String GoodsSkusellStatusofflineS(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                String sqltextback=(String) map.get("sqltextback");
                try{
                    RepResult repResult=this.MtDao.GoodsSkusellStatusD(data);
                    if(repResult!=null && repResult.getResult().equals("1")){

                        Map preMap=map;
                        preMap.put("sqltext",sqltextback);

                        String afterMapdata= JSON.toJSONString(preMap);
                        log.info("商品下架 转出来的json格式 看下格式 ： {} ",afterMapdata);
                        //这里符合我们上架规则才可以上架
                        map.remove("virtualshopid");
                        map.remove("phone");
                        map.remove("goodsId");
                        map.remove("sqltextback");
                        Map parmsMap = CommonUtilImpl.clearMap(map);
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMap);
                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                    }else {
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_GOODONLINE.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_GOODONLINE.getMesssage(),""));
                    }
//                    Map preMap=map;
//                    preMap.put("sqltext",sqltextback);
//
//                    String afterMapdata= JSON.toJSONString(preMap);
//                    log.info("商品下架 转出来的json格式 看下格式 ： {} ",afterMapdata);
//                    //这里符合我们上架规则才可以上架
//                    map.remove("virtualshopid");
//                    map.remove("phone");
//                    map.remove("goodsId");
//                    map.remove("sqltextback");
//                    Map parmsMap = CommonUtilImpl.clearMap(map);
//
//                    String afterMapdata1= JSON.toJSONString(map);
//                    log.info("上传的参数美团 看下格式 ： {} ",afterMapdata1);
//
//                    resultString = URLFactoryByZ.requestApi(method,
//                            url, systemParamsMap, parmsMap);
//                    resultString=CommonUtilImpl.checkGoodsSkusellStatus(resultString,afterMapdata,MtDao);

                }catch (Exception e){
                    log.error("商品下架 调用我们的过程出错了 {}",e.getMessage());
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
