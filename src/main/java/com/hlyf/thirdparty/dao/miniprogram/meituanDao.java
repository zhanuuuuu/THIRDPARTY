package com.hlyf.thirdparty.dao.miniprogram;


import com.hlyf.thirdparty.domain.LoginResult;
import com.hlyf.thirdparty.domain.RepResult;
import com.hlyf.thirdparty.domain.StoreGoodsInfo;
import com.hlyf.thirdparty.domain.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019-02-28.
 */
@Mapper
public interface meituanDao {

    Integer ExecProce(@Param("callJsonText")String callJsonText);;

    RepResult ExecProceGetData(@Param("callJsonText")String callJsonText);

    List<RepResult> ExecProceGetDataList(@Param("callJsonText")String callJsonText);

    List<LoginResult> ExecProceLogin(@Param("callJsonText")String callJsonText);

    List<StoreGoodsInfo> GetStoreGoodsInfo(@Param("callJsonText")String callJsonText);

    //获取门店的
    List<StoreInfo> GetStoreInfo(@Param("callJsonText")String callJsonText);
    RepResult GoodsSkusellStatusD(@Param("callJsonText")String callJsonText);
}
