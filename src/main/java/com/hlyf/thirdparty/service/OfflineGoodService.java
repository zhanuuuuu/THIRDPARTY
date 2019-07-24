package com.hlyf.thirdparty.service;

import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-11.
 */
public interface OfflineGoodService {

    String SearchVirtualShopGoodsS(String callJsonText);

    String CheckVirtualShopGoodsPrice(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String AddVirtualShopGoods(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;
    String EditVirtualShopGoods(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String AddGoodsCategory(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String DeleteGoodsCategoryS(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String DeleteVirtualShopGoodsS(Map map, String data, String url, String method)
            throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String ChangeVirtualShopGoodsStock(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String GetGoodsCategories(Map map, String data, String url, String method) throws ApiSysException,
            ApiOpException, UnsupportedEncodingException;

    String GetGoodsselChangeStoreGoodsPriceLogs(Map map, String data, String url, String method)
            throws ApiSysException, ApiOpException, UnsupportedEncodingException;






}
