package com.hlyf.thirdparty.service;

import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import org.apache.ibatis.annotations.Param;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-11.
 */
public interface MtStoreService {


    String GetStoreInfoS(String callJsonText);

    String GetStoreGoodsInfoS(String callJsonText);

    String SyncShop(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;
}
