package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-18.
 * -- 	小程序总部 获取门店信息准备修改GetVirtualShop
 -- 	in:(virtualshopName:可以传门店ID，门店名称模糊查询，传空显示所有门店)
 -- out:(result：查询成功返回1，查询失败返回0 	virtualshopid:门店编号 virtualshopName:门店名称 o2ochannelId:渠道编号	o2ochannelName:渠道名称
 -- longitude:经度		latitude:纬度 kfphone:客服电话 shipping_fee:订单配送费 shipping_time:门店营业时间 open_level:门店的营业状态：1为可配送，3为休息中
 -- is_online:门店上下线状态：1为上线，0为下线  third_tag_name:门店品类 例如：火锅，特色菜，地方菜 appid:appid appSecret:appSecret
 -- SettleMode:报价模式,O2ORate:O2O渠道方扣点数,ServiceRate:直连商家扣点数,InsuranceRate:保险扣点数,ProfitRate:利润率,FixedOffset:固定费用,MiddleRate:中台服务费

 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class Shop {

    private String  result;
    private String  virtualshopid;
    private String  virtualshopName;
    private String  o2ochannelId;
    private String  o2ochannelName;
    private String  latitude;
    private String  longitude;
    private String  kfphone;
    private String  shipping_fee;
    private String  shipping_time;
    private String  open_level;
    private String  is_online;
    private String  third_tag_name;
    private String  appid;
    private String  appSecret;
    private String  SettleMode;
    private String  O2ORate;
    private String  ServiceRate;
    private String  InsuranceRate;
    private String  ProfitRate;
    private String  FixedOffset;
    private String  MiddleRate;
}
