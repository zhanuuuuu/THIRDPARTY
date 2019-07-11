package com.hlyf.thirdparty.service;

import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019-07-11.
 */
public interface MtStoreService {


    String GetStoreInfoS(String callJsonText);

    String GetStoreGoodsInfoS(String callJsonText);
}
