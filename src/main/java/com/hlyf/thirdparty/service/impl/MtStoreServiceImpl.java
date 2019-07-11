package com.hlyf.thirdparty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hlyf.thirdparty.dao.miniprogram.MtStoreDao;
import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019-07-11.
 */
@Slf4j
@Service
public class MtStoreServiceImpl implements MtStoreService {

    @Autowired
    private MtStoreDao mtStoreDao;

    @Override
    public String GetStoreInfoS(String callJsonText) {
        try{
            List<StoreInfo> storeInfoList=this.mtStoreDao.GetStoreInfo(callJsonText);
            if(storeInfoList!=null){
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
            if(storeInfoList!=null){
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
}
