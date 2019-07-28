package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.RepResult;
import com.hlyf.thirdparty.domain.UserInfo;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.tool.MapRemoveNullUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-06.
 */
@Slf4j
public class CommonUtilImpl {

    public static Map clearMap(Map map) {
        map.remove("sqltext");
        map.remove("O2OChannelId");
        map.remove("appId");
        map.remove("appSecret");
        MapRemoveNullUtil.removeNullValue(map);

        return map;
    }

    public static String MyExecProce(String resultString, String data, meituanDao MtDao){
        log.info("我是拿到的返回值  :{}",resultString);
        if(!resultString.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(resultString);
            if(jsonObject.containsKey("data") && jsonObject.getString("data").equals("ok")){
                try{
                    if(MtDao.ExecProce(data)>0){
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
                        new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
            }
        }else {
            resultString=JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
        }
        return resultString;
    }

    public static String checkGoodsSkusellStatus(String resultString, String data, meituanDao MtDao){
        log.info("我是拿到的返回值  :{}",resultString);
        if(!resultString.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(resultString);
            if(jsonObject.containsKey("data") && jsonObject.getString("data").equals("ok")){
                try{
                    if(MtDao.ExecProce(data)>0){
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
                        new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
            }
        }else {
            resultString=JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
        }
        return resultString;
    }


    public static String CommExe(String resultString, String data, meituanDao MtDao){
        log.info("我是拿到的返回值  :{}",resultString);
        if(!resultString.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(resultString);
            if(jsonObject.containsKey("data") && jsonObject.getString("data").equals("ok")){
                try{
                    RepResult repResult=MtDao.ExecProceGetData(data);
                    if(repResult!=null && repResult.getResult().equals("1")){
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
                        new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
            }
        }else {
            resultString=JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
        }
        return resultString;
    }

    /**
     *
     * @param resultString
     * @param data
     * @param MtDao
     * @param preMap
     * @return
     */
    public static String CommSySn(String resultString, String data, meituanDao MtDao,Map<String,String> preMap){

        String result="{";
        log.info("我是拿到的返回值  :{}",resultString);
        if(!resultString.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(resultString);
            if(jsonObject.containsKey("data") && !jsonObject.containsKey("error")){
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                if(jsonArray.size()>0){
                    for(int i=0;i<jsonArray.size();i++){
                        System.out.println(jsonArray.getJSONObject(i).get("pic_url_large"));

                        result=result+"门店 "+jsonArray.getJSONObject(i).get("name");
                        Map<String,Object> maprequest = JSON.parseObject(jsonArray.getJSONObject(i).toJSONString(),Map.class);
                        Map<String,String> new_map_String = new HashMap();
                        //添加原始数据
                        for(Object key:preMap.keySet()){
                            new_map_String.put(key+"", String.valueOf(preMap.get(key)==null? "":preMap.get(key)));
                        }
                        //添加访问请取回来的数据
                        for(Object key:maprequest.keySet()){
                            new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                        }
                        String callJsonText=JSON.toJSONString(new_map_String,
                                SerializerFeature.WriteNullStringAsEmpty,
                                SerializerFeature.WriteNullBooleanAsFalse);
                        log.info("我是循环访问接口的数据  :{}",callJsonText);
                        try{
                            RepResult repResult=MtDao.ExecProceGetData(callJsonText);
                            if(repResult!=null && repResult.getResult().equals("1")){
                                result=result+"同步成功,";
                            }else{
                                result=result+"同步失败或线下不存在该门店,";
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            result=result+"（调用过程异常）同步失败或线下不存在该门店,";
                        }
                    }
                    //遍历访问结束
                    result=result+"}";
                    resultString=JSONObject.toJSONString(
                            new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",result,resultString));

                }else{
                    resultString=JSONObject.toJSONString(
                            new ResultMsg(true, GlobalEumn.NO_STORE_SYSN.getCode()+"",GlobalEumn.NO_STORE_SYSN.getMesssage(),resultString));
                }


            }else {
                resultString=JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
            }
        }else {
            resultString=JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PARAMETERS_ERROR.getCode()+"",GlobalEumn.PARAMETERS_ERROR.getMesssage(),resultString));
        }
        return resultString;
    }


    public static String getSelResultComm(String data, meituanDao mtDao,String urlTitle) {
        try{
            List<RepResult> repResult= mtDao.ExecProceGetDataList(data);
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
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +urlTitle+" 调用我们的过程出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                            GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
        }
    }

    public static String getUserInfoComm(String data, meituanDao mtDao,String urlTitle) {
        try{
            List<UserInfo> repResult= mtDao.GetUserInfo(data);
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
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +urlTitle+" 调用我们的过程出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.SYSTEM_ERROR.getCode()+"",
                            GlobalEumn.SYSTEM_ERROR.getMesssage(),""));
        }
    }


    public static String CommExecProce(String data, meituanDao mtDao,String urlTitle) {
        try{
            RepResult repResult= mtDao.ExecProceGetData(data);
            if(repResult!=null &&  repResult.getResult().equals("1")){
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.SUCCESS.getCode()+"",
                                GlobalEumn.SUCCESS.getMesssage(),repResult));
            }else {
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.PROCE_ERROR.getCode()+"",
                                GlobalEumn.PROCE_ERROR.getMesssage(),""));
            }
        }catch (Exception e){
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +urlTitle+" 调用我们的过程出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PROCE_ERROR.getCode()+"",
                            GlobalEumn.PROCE_ERROR.getMesssage(),""));
        }
    }

    public static String CommExecProcePushSend(String data, meituanDao mtDao,String urlTitle) {
        try{
            RepResult repResult= mtDao.ExecProceGetData(data);
            if(repResult!=null &&  repResult.getResult().equals("1")){
                return "{\"data\":\"ok\"}";
            }else {
                return JSONObject.toJSONString(
                        new ResultMsg(true, GlobalEumn.PROCE_ERROR.getCode()+"",
                                GlobalEumn.PROCE_ERROR.getMesssage(),""));
            }
        }catch (Exception e){
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() +urlTitle+" 调用我们的过程出错了 {}",e.getMessage());
            return JSONObject.toJSONString(
                    new ResultMsg(true, GlobalEumn.PROCE_ERROR.getCode()+"",
                            GlobalEumn.PROCE_ERROR.getMesssage(),""));
        }
    }
}
