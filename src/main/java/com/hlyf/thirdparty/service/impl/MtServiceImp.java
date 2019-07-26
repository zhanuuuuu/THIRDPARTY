package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.config.MtpushConfig;
import com.hlyf.thirdparty.dao.miniprogram.MtStoreDao;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.*;
import com.hlyf.thirdparty.mertuanoverwrite.SignGeneratorByZ;
import com.hlyf.thirdparty.mertuanoverwrite.URLFactoryByZ;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtService;

import com.hlyf.thirdparty.tool.MapRemoveNullUtil;
import com.sankuai.meituan.waimai.opensdk.constants.ErrorEnum;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import com.sankuai.meituan.waimai.opensdk.factory.URLFactory;
import com.sankuai.meituan.waimai.opensdk.util.ConvertUtil;
import com.sankuai.meituan.waimai.opensdk.util.HttpUtil;
import com.sankuai.meituan.waimai.opensdk.util.PropertiesUtil;
import com.sankuai.meituan.waimai.opensdk.util.SignGenerator;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


/**
 * Created by Administrator on 2019-06-24.
 * 美团的业务逻辑都在这里
 */
@Service
@Slf4j
public class MtServiceImp implements MtService,MtpushConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private meituanDao MtDao;

    @Autowired
    private MtStoreDao mtStoreDao;
    /**
     *
     * @param appId
     * @param appSecret
     * @param requestMethod   请求方法 POST  GET
     * @param meituanurl      美团的url
     * @param request
     * @return
     */
    @Override
    public String transmitS(String appId, String appSecret,
                            String requestMethod,String meituanurl,
                            HttpServletRequest request) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 获取到的参数:\n  appId={} \n " +
                "appSecret={}\n requestMethod={}\n meituanurl={} ",
                appId, appSecret, requestMethod, meituanurl);
        URLFactoryByZ.beforeMethod(appId, appSecret, requestMethod, meituanurl);
        Map systemParamsMap = URLFactoryByZ.getsystemParamsMap(appId, appSecret);
        Map parmsMap = URLFactoryByZ.getparamsMap(request);
        String resultString = URLFactoryByZ.requestApi(requestMethod, meituanurl, systemParamsMap, parmsMap);
        return resultString;
    }

    @Override
    public String poistateupdateS(HttpServletRequest request,String appSecret,String sig) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String url = request.getScheme()+"://"+ request.getServerName()
                +(request.getServerPort()==80 ? "":":"+request.getServerPort())+request.getRequestURI()
                +(request.getQueryString()== null ? "":"?"+request.getQueryString());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" url ="+url);
        String url2=request.getScheme()+"://"+ request.getServerName();
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 协议名：//域名="+url2);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 获取项目名="+request.getContextPath());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 获取参数="+request.getQueryString());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 获取全路径="+request.getRequestURL());

        String urlPrefix=request.getRequestURL().toString();
        Map parmsMap = URLFactoryByZ.getparamsMap(request);
        String urlForGenSig = URLFactoryByZ.genUrlForGenSigByZ(urlPrefix, parmsMap,null);
        String sigByMe = SignGenerator.genSig(urlForGenSig);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 解析出来的签名:{}",sigByMe);
        //签名一致  进行相关操作
        if(sigByMe.equals(sig)){
            log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" {}","进入到了真正的操作了");
        }
        return "{\"data\":\"ok\"}";
    }

    @Override
    public String getpushS(HttpServletRequest request, String appSecret, String sig, String urlType,String app_id) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        //得到秘钥
        Map<String,String> mapProce=new HashMap<String, String>();
        mapProce.put("sqltext","getAppsercret");
        mapProce.put("appid",app_id);
        mapProce.put("O2OChannelId","1");
        MtConfig mtConfig=this.MtDao.GetMtConfig(JSONObject.toJSONString(mapProce));
        //重新赋值appSecret
        appSecret=mtConfig.getAppSecret()==null ? appSecret:mtConfig.getAppSecret();
        String result="{\"data\":\"ok\"}";
        //得到访问的路径
        String urlPrefix=request.getRequestURL().toString();
        Map parmsMap = URLFactoryByZ.getparamsMap(request);
        parmsMap.put("appSecret",appSecret);
        String urlForGenSig = URLFactoryByZ.genUrlForGenSigByZ(urlPrefix, parmsMap,null);
        System.out.println(" 我计算的签名前的字符串 :"+urlForGenSig);
        String sigByMe = SignGeneratorByZ.genSig(urlForGenSig);
        System.out.println(" 原来的签名:"+sig);
        System.out.println(" 我计算的签名:"+sigByMe);
        //签名一致  进行相关操作
        if(sigByMe.equals(sig)){
            //JDK8 支持字符串
            System.out.println(" 我进来了 签名一直:{}"+sigByMe);
            switch (urlType){
                case STORESTATEUPDATE:

                    break;
                case GOODADD:

                    break;
                case GOODUPDATE:

                    break;
                case ORDERSENDSTATE:

                    break;
                case ORDERHASPAY:

                    break;

                case ORDERSURE://已确认订单  暂时接受这个推送
                    parmsMap.put("sqltext","SyncOrder");
                    parmsMap.put("appId",app_id);
                    parmsMap.put("appSecret",appSecret);
                    parmsMap.put("O2OChannelId","1");
                    String detail=request.getParameter("detail");
                    log.info("detail {}",detail);
                    JSONArray  detailArrayList = JSONObject.parseArray(detail);
                    for(int i=0;i<detailArrayList.size();i++){
                        String app_food_code=detailArrayList.getJSONObject(i).getString("app_food_code");
                        parmsMap.put("app_food_code",app_food_code);
                        String data=JSONObject.toJSONString(parmsMap);
                        log.info("我是推送的美团已确认订单,转成json访问过程的数据为 {}",data);
                        result=CommonUtilImpl.CommExecProcePushSend(data,this.MtDao,"已近确认订单 ");
                    }
                    break;
                case ORDERCOMPLETE://已完成订单

                    break;

                default:
                    log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" 我进来了 签名一直:{}",sigByMe);
                    System.out.println(" 我进来了 签名一直:{}"+sigByMe);
                    break;
            }
        }
        return result;
    }

    @Override
    public String poisaveS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                Map parmsMap = clearMap(map);
                resultString = URLFactoryByZ.requestApi("POST",
                        "https://waimaiopen.meituan.com/api/v1/poi/save", systemParamsMap, parmsMap);
                resultString=this.MyExecProce(resultString,data);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }

        return resultString;

    }

    @Override
    public String CreateShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProce(dataTrue,this.MtDao,"创建门店接口(线下)");
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;

    }

    @Override
    public String AddVirtualShopGoodsPriceRuleS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProce(dataTrue,this.MtDao,"小程序总部 添加调价规则AddVirtualShopGoodsPriceRule(线下)");
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;

    }


    @Override
    public String EditVirtualShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                log.info("原始数据 {} ",data);
                String afterMapdata= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",afterMapdata);

                Map<String,String> parmsMapTrue=new HashMap<>();
                parmsMapTrue.put("app_poi_code",(String)map.get("virtualshopid"));
                parmsMapTrue.put("name",(String)map.get("name"));
                parmsMapTrue.put("address",(String)map.get("address"));
                parmsMapTrue.put("latitude",(String)map.get("longitude"));
                parmsMapTrue.put("longitude",(String)map.get("latitude"));
                parmsMapTrue.put("phone",(String)map.get("kfphone"));
                parmsMapTrue.put("shipping_fee",(String)map.get("shipping_fee"));
                parmsMapTrue.put("shipping_time",(String)map.get("shipping_time"));
                parmsMapTrue.put("open_level",(String)map.get("open_level"));
                parmsMapTrue.put("is_online",(String)map.get("is_online"));

                parmsMapTrue.put("invoice_support",(String)map.get("invoice_support"));
                parmsMapTrue.put("invoice_min_price",(String)map.get("invoice_min_price"));
                parmsMapTrue.put("invoice_description",(String)map.get("invoice_description"));

                parmsMapTrue.put("third_tag_name",(String)map.get("third_tag_name"));
                parmsMapTrue.put("pre_book",(String)map.get("pre_book"));
                parmsMapTrue.put("time_select",(String)map.get("time_select"));

                //TODO 暂时先屏蔽掉美团的访问 后续开放这里
                resultString="{\"data\":\"ok\"}";
//                resultString = URLFactoryByZ.requestApi("POST",
//                        "https://waimaiopen.meituan.com/api/v1/poi/save", systemParamsMap, parmsMapTrue);
                resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;

    }

    @Override
    public String DeleteVirtualShopGoodsPriceRuleS(Map map, String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProce(dataTrue,this.MtDao,"小程序总部 删除调价规则 DeleteVirtualShopGoodsPriceRule (线下)");
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }

    @Override
    public String seltempShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);

                try{
                    List<TempShop> repResult= mtStoreDao.GetTempShop(data);
                    if(repResult!=null && repResult.size()>0 ){
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                        GlobalEumn.SUCCESS.getMesssage(),repResult));
                    }else {
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
                    }
                }catch (Exception e){
                    log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +" 调用我们的过程出错了 {}",e.getMessage());
                    resultString= JSONObject.toJSONString(
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
    public String GetVirtualShopGoodsPriceRulesS(Map map, String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                try{
                    List<ShopGoodsPriceRules> repResult= mtStoreDao.GetShopGoodsPriceRules(data);
                    if(repResult!=null && repResult.size()>0 ){
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                        GlobalEumn.SUCCESS.getMesssage(),repResult));
                    }else {
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
                    }
                }catch (Exception e){
                    log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +" 调用我们的过程出错了 {}",e.getMessage());
                    resultString= JSONObject.toJSONString(
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
    public String GetVirtualShopS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                try{
                    List<Shop> repResult= mtStoreDao.GetShop(data);
                    if(repResult!=null && repResult.size()>0 ){
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                        GlobalEumn.SUCCESS.getMesssage(),repResult));
                    }else {
                        resultString= JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
                    }
                }catch (Exception e){
                    log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +" 小程序总部 获取门店信息准备修改GetVirtualShop 出错了 {}",e.getMessage());
                    resultString= JSONObject.toJSONString(
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

    private Map clearMap(Map map) {
        map.remove("sqltext");
        map.remove("O2OChannelId");
        map.remove("appId");
        map.remove("appSecret");
        MapRemoveNullUtil.removeNullValue(map);
        return map;
    }



    private String MyExecProce(String resultString,String data){
        log.info("我是拿到的返回值  :{}",resultString);
        if(!resultString.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(resultString);
            if(jsonObject.containsKey("data") && jsonObject.getString("data").equals("ok")){
                try{
                    if(this.MtDao.ExecProce(data)>0){
                        resultString=JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",GlobalEumn.SUCCESS.getMesssage(),resultString));
                    }else{
                        resultString=JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS_ERROR.getCode()+"",GlobalEumn.SUCCESS_ERROR.getMesssage(),resultString));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    resultString=JSONObject.toJSONString(
                            new ResultMsg(true, GlobalEumn.SUCCESS_ERROR.getCode()+"",GlobalEumn.SUCCESS_ERROR.getMesssage(),resultString));
                }

            }else {
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.SUCCESS_ERROR.getCode()+"",GlobalEumn.SUCCESS_ERROR.getMesssage(),resultString));
            }
        }else {
            resultString=JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
        }
        return resultString;
    }


    @Override
    public String SetShopStatusS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                log.info("原始数据 {} ",data);
                String afterMapdata= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",afterMapdata);
                String StatusS=(String)map.get("Status");
                String url;
                Map<String,String> parmsMapTrue=new HashMap<>();
                parmsMapTrue.put("app_poi_code",(String)map.get("virtualshopid"));
                switch (StatusS) {
                    case "open":
                        url="https://waimaiopen.meituan.com/api/v1/poi/open";
                        break;
                    case "close":
                        url="https://waimaiopen.meituan.com/api/v1/poi/close";
                        break;
                    case "offline":
                        url="https://waimaiopen.meituan.com/api/v1/poi/offline";
                        break;
                    case "online":
                        url="https://waimaiopen.meituan.com/api/v1/poi/online";
                        break;
                    default:
                        url="https://waimaiopen.meituan.com/api/v1/poi/online";
                        break;

                }
                resultString = URLFactoryByZ.requestApi("POST",
                        url, systemParamsMap, parmsMapTrue);
                resultString=CommonUtilImpl.CommExe(resultString,data,MtDao);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }

        return resultString;
    }

    @Override
    public String poicloseS(Map map,String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                Map parmsMap = clearMap(map);
                resultString = URLFactoryByZ.requestApi("POST",
                        "https://waimaiopen.meituan.com/api/v1/poi/close", systemParamsMap, parmsMap);
                resultString=this.MyExecProce(resultString,data);

                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }

        return resultString;


    }


    @Override
    public String poiStoreS(Map map,String data,String url,String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                Map parmsMap = clearMap(map);
                resultString = URLFactoryByZ.requestApi(method,
                        url, systemParamsMap, parmsMap);
                resultString=this.MyExecProce(resultString,data);

                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }


}
