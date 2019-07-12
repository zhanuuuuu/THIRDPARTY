package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-09.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class StoreGoodsInfo {

    private String result;
    private String virtualshopid;
    private String virtualshopName;
    private String o2ochannelId;
    private String o2ochannelName;
    private String goodsName;
    private String O2OCode;
    private String goodsId;
    private String description;
    private String skus;
    private String ObtainedPrice;
    private String CostPrice;
    private String min_order_count;
    private String unit;
    private String sepc;
    private String box_num;
    private String box_price;
    private String GoodsGroupId;
    private String groupName;
    private String Status;
    private String ImageUrl;
    private String stock;

}
