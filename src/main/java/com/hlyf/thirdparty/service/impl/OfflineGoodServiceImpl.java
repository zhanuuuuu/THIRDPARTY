package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.dao.miniprogram.MtStoreDao;
import com.hlyf.thirdparty.dao.miniprogram.OfflineGoodDao;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.RepResult;
import com.hlyf.thirdparty.domain.SearchGoods;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.hlyf.thirdparty.mertuanoverwrite.URLFactoryByZ;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.OfflineGoodService;
import com.hlyf.thirdparty.tool.MapRemoveNullUtil;
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
@Service
@Slf4j
public class OfflineGoodServiceImpl implements OfflineGoodService {

    @Autowired
    private OfflineGoodDao offlineGoodDao;

    @Autowired
    private meituanDao MtDao;

    @Override
    public String SearchVirtualShopGoodsS(String callJsonText) {
        try{
            List<SearchGoods> storeInfoList=this.offlineGoodDao.GetSearchGoodsByName(callJsonText);
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
    public String CheckVirtualShopGoodsPrice(Map map, String data, String url, String method) throws ApiSysException,
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
                        log.info("商品调价 转出来的json格式 看下格式 ： {} ",afterMapdata);

                        Map<String,String> parmsMapTrue=new HashMap<String,String>();
                        parmsMapTrue.put("app_poi_code",(String)preMap.get("virtualshopid"));

                        String skus="[{\"app_food_code\":\""+(String)preMap.get("GoodsId")+"\"," +
                                "\"skus\":[{\"sku_id\":\""+(String)preMap.get("sku_id")+"\",\"price\":\""+repResult.getMtprice()+"\"}]}]";
                        parmsMapTrue.put("food_data",skus.replaceAll("\\s","").replaceAll("\\n",""));

                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMapTrue);

                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);

                    }else {
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_PRICE.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_PRICE.getMesssage(),""));
                    }
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
    public String AddVirtualShopGoods(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                String sqltextback=(String) map.get("sqltextback");
                try{
                    Map preMap=map;
                    preMap.put("sqltext",sqltextback);

                    RepResult repResult=this.MtDao.ExecProceGetData(data);
                    if(repResult!=null && repResult.getResult().equals("1")){


                        //这里符合我们上架规则才可以上架
                        Map<String,Object> parmsMapTrue=new HashMap<String,Object>();
                        String skus="[{\n" +
                                "\t\"sku_id\": \""+preMap.get("sku_id")+"\",\n" +
                                "\t\"spec\": \""+preMap.get("spec")+"\",\n" +
                                "\t\"price\": \""+repResult.getMtprice()+"\",\n" +
                                "\t\"stock\": \""+preMap.get("stock")+"\",\n" +
                                "\t\"box_num\": \""+preMap.get("box_num")+"\",\n" +
                                "\t\"box_price\": \""+preMap.get("box_price")+"\"\n" +
                                "}]";
                        parmsMapTrue.put("app_poi_code",(String)preMap.get("virtualshopid"));
                        parmsMapTrue.put("operate_type",1+"");
                        parmsMapTrue.put("app_food_code",repResult.getGoodsid());
                        parmsMapTrue.put("name",(String)preMap.get("Name"));
                        parmsMapTrue.put("description",(String)preMap.get("Description"));
                        parmsMapTrue.put("category_code",(String)preMap.get("goodsGroupId"));
                        parmsMapTrue.put("skus",skus.replaceAll("\\s","").replaceAll("\\n",""));
                        parmsMapTrue.put("price",Float.valueOf(repResult.getMtprice())+"");
                        parmsMapTrue.put("min_order_count",(String)preMap.get("min_order_count"));
                        parmsMapTrue.put("unit",(String)preMap.get("Unit"));
                        parmsMapTrue.put("is_sold_out",(String)preMap.get("is_sold_out"));
                        parmsMapTrue.put("picture",(String)preMap.get("ImageUrl"));

                        String parmsMapTrueString= JSON.toJSONString(parmsMapTrue);

                        System.out.println(parmsMapTrueString);
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMapTrue);
                        preMap.put("goodsid",repResult.getGoodsid());
                        String afterMapdata= JSON.toJSONString(preMap);
                        log.info("商品新增 转出来的json格式 看下格式 ： {} ",afterMapdata);

                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                    }else {
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_ADDGOOD.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_ADDGOOD.getMesssage(),""));
                    }

//                    String afterMapdata= JSON.toJSONString(preMap);
//                    log.info("商品新增 转出来的json格式 看下格式 ： {} ",afterMapdata);
//                    //这里符合我们上架规则才可以上架
//                    Map<String,Object> parmsMapTrue=new HashMap<String,Object>();
//                    String skus="[{\n" +
//                            "\t\"sku_id\": \""+preMap.get("sku_id")+"\",\n" +
//                            "\t\"spec\": \""+preMap.get("spec")+"\",\n" +
//                            "\t\"price\": \""+20+"\",\n" +
//                            "\t\"stock\": \""+preMap.get("stock")+"\",\n" +
//                            "\t\"box_num\": \""+preMap.get("box_num")+"\",\n" +
//                            "\t\"box_price\": \""+preMap.get("box_price")+"\"\n" +
//                            "}]";
//                    parmsMapTrue.put("app_poi_code",(String)preMap.get("virtualshopid"));
//                    parmsMapTrue.put("operate_type",1+"");
//                    parmsMapTrue.put("app_food_code",(String)preMap.get("goodsid"));
//                    parmsMapTrue.put("name",(String)preMap.get("Name"));
//                    parmsMapTrue.put("description",(String)preMap.get("Description"));
//                    parmsMapTrue.put("category_code",(String)preMap.get("goodsGroupId"));
//                    parmsMapTrue.put("skus",skus.replaceAll("\\s","").replaceAll("\\n",""));
//                    parmsMapTrue.put("price",20+"");
//                    parmsMapTrue.put("min_order_count",(String)preMap.get("min_order_count"));
//                    parmsMapTrue.put("unit",(String)preMap.get("Unit"));
//                    parmsMapTrue.put("is_sold_out",(String)preMap.get("is_sold_out"));
//                    parmsMapTrue.put("picture",(String)preMap.get("ImageUrl"));
//
//                    String parmsMapTrueString= JSON.toJSONString(parmsMapTrue);
//
//                    System.out.println("看这里：  "+parmsMapTrueString);
//
//                    //MapRemoveNullUtil.removeNullValue(parmsMapTrue);
//
//                    resultString = URLFactoryByZ.requestApi(method,
//                            url, systemParamsMap, parmsMapTrue);
//
//                    resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                }catch (Exception e){
                    e.printStackTrace();
                    log.error("新增商品 调用我们的过程出错了 {}",e.getMessage());
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
    public String AddGoodsCategory(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                String sqltext=(String) map.get("sqltext");
                try{
                        Map preMap=map;
                        preMap.put("GoodsGroupId",(String)preMap.get("category_code") );
                        preMap.put("GoodsGroupName",(String)preMap.get("category_name") );
                        String afterMapdata= JSON.toJSONString(preMap);
                        log.info("创建商品分类 转出来的json格式 看下格式 ： {} ",afterMapdata);
                        Map parmsMap = CommonUtilImpl.clearMap(map);
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMap);
                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                }catch (Exception e){
                    log.error("创建商品分类 调用我们的过程出错了 {}",e.getMessage());
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
    public String ChangeVirtualShopGoodsStock(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                Map systemParamsMap = URLFactoryByZ.getsystemParamsMap((String) map.get("appId"), (String) map.get("appSecret"));
                try{
                        Map preMap=map;
                        String afterMapdata= JSON.toJSONString(preMap);
                        log.info("商品调库存 转出来的json格式 看下格式 ： {} ",afterMapdata);
                        Map<String,String> parmsMapTrue=new HashMap<String,String>();
                        parmsMapTrue.put("app_poi_code",(String)preMap.get("virtualshopid"));

                        String sku="[{\"app_food_code\":\""+(String)preMap.get("goodsId")+"\"," +
                            "\"skus\":[{\"sku_id\":\""+(String)preMap.get("sku_id")+"\",\"stock\":\""+(String)preMap.get("stock")+"\"}]}]";
                        parmsMapTrue.put("food_data",
                                sku.replaceAll("\\s","").replaceAll("\\n","")
                        );
                        resultString = URLFactoryByZ.requestApi(method,
                                url, systemParamsMap, parmsMapTrue);
                        resultString=CommonUtilImpl.CommExe(resultString,afterMapdata,MtDao);
                }catch (Exception e){
                    log.error("更改商品库存 调用我们的过程出错了 {}",e.getMessage());
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
    public String GetGoodsCategories(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                try{
                    List<RepResult> repResult=this.MtDao.ExecProceGetDataList(data);
                    if(repResult!=null && repResult.size()>0 && repResult.get(0).getResult().equals("1")){
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                        GlobalEumn.SUCCESS.getMesssage(),repResult));
                    }else {
                        return JSONObject.toJSONString(
                                new ResultMsg(true, GlobalEumn.MINIPROGRAM_EMPTY.getCode()+"",
                                        GlobalEumn.MINIPROGRAM_EMPTY.getMesssage(),""));
                    }
                }catch (Exception e){
                    log.error("得到商品分类 调用我们的过程出错了 {}",e.getMessage());
                    return JSONObject.toJSONString(
                            new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                                    GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
                }
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }


}
