package com.hlyf.thirdparty.mertuanoverwrite;

/**
 * Created by Administrator on 2019-06-24.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.sankuai.meituan.waimai.opensdk.constants.ErrorEnum;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import com.sankuai.meituan.waimai.opensdk.util.HttpUtil;
import com.sankuai.meituan.waimai.opensdk.util.PropertiesUtil;
import com.sankuai.meituan.waimai.opensdk.util.SignGenerator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
public class URLFactoryByZ {
    private static String urlPrefix = "";
    private static Map<String, String> urlMap = new HashMap();
    private static Map<String, String> urlTypeMap = new HashMap();

    public URLFactoryByZ() {
    }

    public static Map getsystemParamsMap(String appId, String appSecret) {
        Map systemParamsMap= new HashMap<String,String>();
        systemParamsMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        systemParamsMap.put("app_id", appId);
        systemParamsMap.put("appSecret", appSecret);
        return systemParamsMap;
    }

    public static Map getparamsMap(HttpServletRequest request) {
        //请求参数
        Enumeration<String> enumeration = request.getParameterNames();
        Map parmsMap= new HashMap<String,String>();
        String parmsString = "";
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            String paramValue = null;
            try {
                paramValue = URLDecoder.decode(request.getParameterValues(paramName)[0], "UTF-8");
                paramValue=URLDecoder.decode(paramValue, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                paramValue=null;
                System.out.println("解码出错了");
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"解码出错了：\n {} ",paramName);
                e.printStackTrace();
            }
            parmsMap.put(paramName,paramValue );
            parmsString = parmsString + paramName + "=" + paramValue + "&";
        }
        //这里没用
        String parmsData = parmsString.toString().equals("") ? parmsString.toString():parmsString.toString().substring(0,parmsString.toString().length()-1);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"接收到的参数：\n {} ",parmsData);

        if(parmsMap.containsKey("appId")){
            parmsMap.remove("appId");
        }
        if(parmsMap.containsKey("appSecret")){
            parmsMap.remove("appSecret");
        }
        if(parmsMap.containsKey("requestMethod")){
            parmsMap.remove("requestMethod");
        }
        if(parmsMap.containsKey("meituanurl")){
            parmsMap.remove("meituanurl");
        }
        if(parmsMap.containsKey("sig")){
            parmsMap.remove("sig");
        }

        return parmsMap;
    }

    public static String requestApi(String requestMethod, String urlPrefix, Map systemParamsMap, Map parmsMap) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String urlForGenSig = URLFactoryByZ.genUrlForGenSigByZ(urlPrefix, systemParamsMap, parmsMap);
        String sig = SignGeneratorByZ.genSig(urlForGenSig);
        urlForGenSig.replaceAll((String) systemParamsMap.get("appSecret"), "");
        return HttpUtil.request(urlPrefix, genUrlForGetRequest(urlPrefix, systemParamsMap, parmsMap),
                sig, systemParamsMap,
                parmsMap, requestMethod, PropertiesUtil.getRequestConfig());
    }

    public static String genUrlForGetRequest(String urlPrefix, Map<String, String> systemParamsMap, Map<String, String> applicationParamsMap) throws ApiOpException {
        String uriParamStr = "";
        uriParamStr = uriParamStr + "app_id=" + (String)systemParamsMap.get("app_id") + "&timestamp=" + (String)systemParamsMap.get("timestamp");
        if(applicationParamsMap != null && !"null".equals(applicationParamsMap) && !"NULL".equals(applicationParamsMap)) {
            Iterator i$ = applicationParamsMap.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                String val = (String)applicationParamsMap.get(key);
                if(val != null && !"".equals(val) && !"null".equals(val) && !"NULL".equals(val)) {
                    try {
                        key = URLEncoder.encode(key, "UTF-8");
                        val = URLEncoder.encode(val, "UTF-8");
                        uriParamStr = uriParamStr + "&" + key + "=" + val;
                    } catch (UnsupportedEncodingException var8) {
                        throw new ApiOpException(var8);
                    }
                }
            }
        }
        String basedUrl = urlPrefix + "?" + uriParamStr;
        return basedUrl;
    }

    public static void beforeMethod(String appId, String appSecret, String requestMethod, String meituanurl) throws ApiSysException {
        if("POST".equals(requestMethod) || "GET".equals(requestMethod)){

        }else {
            throw new ApiSysException(ErrorEnum.LACK_OF_PARAM);
        }
        if("".equals(appId) || "".equals(appSecret) ||  !meituanurl.contains("http")){
            throw new ApiSysException(ErrorEnum.LACK_OF_PARAM);
        }
    }

    public static String genUrlPrefix(String methodName) throws ApiSysException {
        String env;
        if(urlPrefix.equals("")) {
            env = PropertiesUtil.getEnvironmentMode();
            if("0".equals(env)) {
                urlPrefix = "http://test.waimaiopen.meituan.com/api/v1/";
            } else if("1".equals(env)) {
                urlPrefix = "https://waimaiopen.meituan.com/api/v1/";
            } else if("2".equals(env)) {
                urlPrefix = "http://127.0.0.1:9000/api/v1/";
            }
        }
        env = urlPrefix + (String)urlMap.get(methodName);
        return env;
    }

    public static String genUrlType(String methodName) {
        String methodType = (String)urlTypeMap.get(methodName);
        return methodType;
    }

    public static String genUrlForGenSigByZ(String urlPrefix, Map<String, String> systemParamsMap, Map<String, String> applicationParamsMap) throws ApiSysException, UnsupportedEncodingException {
        Map<String, String> paramMap = new HashMap();
        paramMap.putAll(systemParamsMap);
        if(applicationParamsMap != null) {
            paramMap.putAll(applicationParamsMap);
        }

        String str = concatParams(paramMap);
        String basedUrl = urlPrefix + "?" + str + (String)systemParamsMap.get("appSecret");
        System.out.println("我是要签名的字符串 ："+basedUrl);
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"我是要签名的字符串：\n {} ",basedUrl);
        return basedUrl;
    }


    public static String genUrlForGenSig(String methodName, Map<String, String> systemParamsMap, Map<String, String> applicationParamsMap) throws ApiSysException, UnsupportedEncodingException {
        Map<String, String> paramMap = new HashMap();
        paramMap.putAll(systemParamsMap);
        if(applicationParamsMap != null) {
            paramMap.putAll(applicationParamsMap);
        }

        String str = concatParams(paramMap);
        String basedUrl = genUrlPrefix(methodName) + "?" + str + (String)systemParamsMap.get("appSecret");
        return basedUrl;
    }

    public static String genOnlyHasSysParamsAndSigUrl(String urlPrefix, Map<String, String> systemParamsMap, String sig) throws UnsupportedEncodingException {
        String str = concatParams(systemParamsMap);
        String basedUrl = urlPrefix + "?" + str + "&sig=" + sig;
        return basedUrl;
    }

    public static String genNotOnlySysParamsUrlForGetRequest(String urlPrefix, Map<String, String> systemParamsMap, String sig, Map<String, String> otherParamsMap) throws UnsupportedEncodingException {
        systemParamsMap.putAll(otherParamsMap);
        String str = concatParams(systemParamsMap);
        String handledUrl = urlPrefix + "?" + str + "&sig=" + sig;
        return handledUrl;
    }

    public static String genUrlForGetRequest(String urlHasParamsNoSig, String sig) {
        String handledUrl = urlHasParamsNoSig + "&sig=" + sig;
        return handledUrl;
    }

    private static String concatParams(Map<String, String> params2) throws UnsupportedEncodingException {
        Object[] key_arr = params2.keySet().toArray();
        Arrays.sort(key_arr);
        String str = "";
        Object[] arr$ = key_arr;
        int len$ = key_arr.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object key = arr$[i$];
            if(!key.equals("appSecret")) {
                String val = (String)params2.get(key);
                str = str + "&" + key + "=" + URLDecoder.decode(val, "utf-8");
            }
        }

        return str.replaceFirst("&", "");
    }

    static {
        urlMap.put("poiSave", "poi/save");
        urlMap.put("poiGetIds", "poi/getids");
        urlMap.put("poiMget", "poi/mget");
        urlMap.put("poiOpen", "poi/open");
        urlMap.put("poiClose", "poi/close");
        urlMap.put("poiOffline", "poi/offline");
        urlMap.put("poiOnline", "poi/online");
        urlMap.put("poiQualifySave", "poi/qualify/save");
        urlMap.put("poiSendTimeSave", "poi/sendtime/save");
        urlMap.put("poiAdditionalSave", "poi/additional/save");
        urlMap.put("poiUpdatepromoteinfo", "poi/updatepromoteinfo");
        urlMap.put("poiList", "poi/list");
        urlMap.put("poiTagList", "poiTag/list");
        urlMap.put("poiUpdatePromotionInfo", "poi/updatepromoteinfo");
        urlTypeMap.put("poiSave", "POST");
        urlTypeMap.put("poiGetIds", "GET");
        urlTypeMap.put("poiMget", "GET");
        urlTypeMap.put("poiOpen", "POST");
        urlTypeMap.put("poiClose", "POST");
        urlTypeMap.put("poiOffline", "POST");
        urlTypeMap.put("poiOnline", "POST");
        urlTypeMap.put("poiQualifySave", "POST");
        urlTypeMap.put("poiSendTimeSave", "POST");
        urlTypeMap.put("poiAdditionalSave", "POST");
        urlTypeMap.put("poiUpdatepromoteinfo", "POST");
        urlTypeMap.put("poiList", "GET");
        urlTypeMap.put("poiTagList", "post");
        urlTypeMap.put("poiUpdatePromotionInfo", "post");
        urlMap.put("shippingSave", "shipping/save");
        urlMap.put("shippingList", "shipping/list");
        urlMap.put("shippingBatchSave", "shipping/batchsave");
        urlMap.put("thirdShippingSave", "third_shipping/save");
        urlMap.put("thirdShippingDelete", "third_shipping/delete");
        urlTypeMap.put("shippingSave", "POST");
        urlTypeMap.put("shippingList", "GET");
        urlTypeMap.put("shippingBatchSave", "POST");
        urlTypeMap.put("thirdShippingSave", "POST");
        urlTypeMap.put("thirdShippingDelete", "POST");
        urlMap.put("foodList", "food/list");
        urlMap.put("foodListByPage", "food/list");
        urlMap.put("foodSave", "food/save");
        urlMap.put("foodBatchSave", "food/batchsave");
        urlMap.put("foodInitData", "food/initdata");
        urlMap.put("foodBatchInitData", "food/batchinitdata");
        urlMap.put("foodDelete", "food/delete");
        urlMap.put("foodSkuSave", "food/sku/save");
        urlMap.put("foodSkuDelete", "food/sku/delete");
        urlMap.put("updateFoodSkuStock", "food/sku/stock");
        urlMap.put("updateFoodSkuPrice", "food/sku/price");
        urlMap.put("incFoodSkuStock", "food/sku/inc_stock");
        urlMap.put("descFoodSkuStock", "food/sku/desc_stock");
        urlMap.put("foodCatList", "foodCat/list");
        urlMap.put("foodCatUpdate", "foodCat/update");
        urlMap.put("foodCatDelete", "foodCat/delete");
        urlTypeMap.put("foodList", "GET");
        urlTypeMap.put("foodSave", "POST");
        urlTypeMap.put("foodBatchSave", "POST");
        urlTypeMap.put("foodInitData", "POST");
        urlTypeMap.put("foodBatchInitData", "POST");
        urlTypeMap.put("foodDelete", "POST");
        urlTypeMap.put("foodSkuSave", "POST");
        urlTypeMap.put("foodSkuDelete", "POST");
        urlTypeMap.put("updateFoodSkuStock", "POST");
        urlTypeMap.put("updateFoodSkuPrice", "POST");
        urlTypeMap.put("incFoodSkuStock", "POST");
        urlTypeMap.put("descFoodSkuStock", "POST");
        urlTypeMap.put("foodCatList", "GET");
        urlTypeMap.put("foodCatUpdate", "POST");
        urlTypeMap.put("foodCatDelete", "POST");
        urlMap.put("medicineCatSave", "medicineCat/save");
        urlMap.put("medicineCatUpdate", "medicineCat/update");
        urlMap.put("medicineCatDelete", "medicineCat/delete");
        urlMap.put("medicineCatList", "medicineCat/list");
        urlMap.put("medicineList", "medicine/list");
        urlMap.put("medicineSave", "medicine/save");
        urlMap.put("medicineBatchSave", "medicine/batchsave");
        urlMap.put("medicineUpdate", "medicine/update");
        urlMap.put("medicineBatchUpdate", "medicine/batchupdate");
        urlMap.put("medicineDelete", "medicine/delete");
        urlTypeMap.put("medicineCatSave", "POST");
        urlTypeMap.put("medicineCatUpdate", "POST");
        urlTypeMap.put("medicineCatDelete", "POST");
        urlTypeMap.put("medicineCatList", "GET");
        urlTypeMap.put("medicineList", "GET");
        urlTypeMap.put("medicineSave", "POST");
        urlTypeMap.put("medicineBatchSave", "POST");
        urlTypeMap.put("medicineUpdate", "POST");
        urlTypeMap.put("medicineBatchUpdate", "POST");
        urlTypeMap.put("medicineDelete", "POST");
        urlMap.put("imageUpload", "image/upload");
        urlTypeMap.put("imageUpload", "POST");
        urlMap.put("orderConfirm", "order/confirm");
        urlMap.put("orderReceived", "order/poi_received");
        urlMap.put("orderCancel", "order/cancel");
        urlMap.put("orderDelivering", "order/Delivering");
        urlMap.put("orderArrived", "order/arrived");
        urlMap.put("orderRefundAgree", "order/refund/agree");
        urlMap.put("orderRefundReject", "order/refund/reject");
        urlMap.put("orderSubsidy", "order/subsidy");
        urlMap.put("orderViewStatus", "order/viewstatus");
        urlMap.put("orderGetActDetailByAcId", "order/getActDetailByAcid");
        urlMap.put("orderGetOrderDetail", "order/getOrderDetail");
        urlMap.put("orderLogisticsPush", "order/logistics/push");
        urlMap.put("orderLogisticsCancel", "order/logistics/cancel");
        urlMap.put("orderLogisticsStatus", "order/logistics/status");
        urlTypeMap.put("orderConfirm", "GET");
        urlTypeMap.put("orderReceived", "GET");
        urlTypeMap.put("orderCancel", "GET");
        urlTypeMap.put("orderDelivering", "GET");
        urlTypeMap.put("orderArrived", "GET");
        urlTypeMap.put("orderRefundAgree", "GET");
        urlTypeMap.put("orderRefundReject", "GET");
        urlTypeMap.put("orderSubsidy", "GET");
        urlTypeMap.put("orderViewStatus", "GET");
        urlTypeMap.put("orderGetActDetailByAcId", "GET");
        urlTypeMap.put("orderGetOrderDetail", "GET");
        urlTypeMap.put("orderLogisticsPush", "GET");
        urlTypeMap.put("orderLogisticsCancel", "GET");
        urlTypeMap.put("orderLogisticsStatus", "GET");
    }
}
