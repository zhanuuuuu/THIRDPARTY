package com.hlyf.thirdparty.service;

import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-28.
 */
public interface UserConntrollerService {

    String AddUserS(Map map, String data) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String UserComm(Map map, String data,String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String SelectUserInfoComm(Map map,String data,String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException;

    String UserCommUpdateUserInfo(Map map, String data, String title) throws ApiSysException, ApiOpException, UnsupportedEncodingException;
}
