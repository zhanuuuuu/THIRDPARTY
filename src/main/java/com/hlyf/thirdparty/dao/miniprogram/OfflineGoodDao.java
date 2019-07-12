package com.hlyf.thirdparty.dao.miniprogram;

import com.hlyf.thirdparty.domain.SearchGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019-07-11.
 */
public interface OfflineGoodDao {

    List<SearchGoods> GetSearchGoodsByName(@Param("callJsonText")String callJsonText);
}
