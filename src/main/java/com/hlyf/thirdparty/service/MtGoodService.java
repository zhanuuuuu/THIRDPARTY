package com.hlyf.thirdparty.service;

import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-06.
 */
public interface MtGoodService {

    String GoodsUpdateS(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String GoodsSkusellStatusS(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    //商品下架
    String GoodsSkusellStatusofflineS(Map map, String data, String url, String method) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

}
