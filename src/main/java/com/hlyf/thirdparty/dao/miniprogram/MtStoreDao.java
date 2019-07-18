package com.hlyf.thirdparty.dao.miniprogram;

import com.hlyf.thirdparty.domain.Shop;
import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import com.hlyf.thirdparty.domain.TempShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019-07-11.
 */
@Mapper
public interface MtStoreDao {

    //获取门店的
    List<StoreInfo> GetStoreInfo(@Param("callJsonText")String callJsonText);

    List<StoreGoodsInfo> GetStoreGoodsInfo(@Param("callJsonText")String callJsonText);

    List<TempShop> GetTempShop(@Param("callJsonText")String callJsonText);

    List<Shop> GetShop(@Param("callJsonText")String callJsonText);

}
