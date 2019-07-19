package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-19.
 * 小程序总部 查询调价规则GetVirtualShopGoodsPriceRules
 -- 	in:(Id:规则ID,virtualshopid:门店编号 GoodsId:商品ID,GoodsGroupName:类别名称,LowerRate:允许下调率（大于0小于1格式0.2）,LowerRate:允许上浮率（大于0小于1格式0.2），
 -- StartTime：规则开始时间,EndTime：规则结束时间
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class ShopGoodsPriceRules {

    private String result;
    private String Id;
    private String goodsid;
    private String VirtualShopId;
    private String GoodsGroupName;
    private String LowerRate;
    private String UpperRate;
    private String CreationTime;
    private String StartTime;
    private String EndTime;
}
