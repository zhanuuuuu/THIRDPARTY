package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.dao.miniprogram.MtStoreDao;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.domain.TempShop;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.UserConntrollerService;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-28.
 */
@Service
@Slf4j
public class UserConntrollerServiceImpl implements UserConntrollerService {

    @Autowired
    private MtStoreDao mtStoreDao;

    @Autowired
    private meituanDao MtDao;


    @Override
    public String AddUserS(Map map, String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProceAddUserOrUpdateUserInfo(dataTrue,this.MtDao,"小程序总部 添加调价规则AddVirtualShopGoodsPriceRule(线下)");
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;

    }


    @Override
    public String UserCommUpdateUserInfo(Map map, String data, String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProceAddUserOrUpdateUserInfo(dataTrue,this.MtDao,title);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }

    @Override
    public String UserComm(Map map, String data, String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException {
        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                String dataTrue= JSON.toJSONString(map);
                log.info("转换出来的请求过程的数据 {} ",dataTrue);
                resultString=CommonUtilImpl.CommExecProceAddUserOrUpdateUserInfo(dataTrue,this.MtDao,title);
                break;
            default:
                log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"{}",data);
                break;
        }
        return resultString;
    }

    @Override
    public String SelectUserInfoComm(Map map,String data,String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException {

        String resultString="";
        Integer type=Integer.valueOf((String) map.get("O2OChannelId"));
        switch (type){
            case 1://美团
                log.info("原始数据 {} ",data);
                try{
                    resultString=CommonUtilImpl.getUserInfoComm(data,this.MtDao,title);
                }catch (Exception e){
                    log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+title +" 调用我们的过程出错了 {}",e.getMessage());
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


}
