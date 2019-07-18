package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.RepResult;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.tool.MapRemoveNullUtil;
import lombok.extern.slf4j.Slf4j;

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
}
