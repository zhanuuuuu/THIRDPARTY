package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-10.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class RepResult {
    private String result;
    private String  newPrice;
    private String  mtprice;
    private String  goodsid;

    /*
    * 接收类别的
    */
    private String GoodsGroupId;
    private String  GoodsGroupName;

    /**
     * 小程序门店
     * 查询门店调价记录
     * selChangeStoreGoodsPriceLog
     */
    private String goodsName;
    private String VirtualShopId;
    private String ObtainedPrice;
    //private String newPrice;
    private String UpdateTime;
    private String context;

}
