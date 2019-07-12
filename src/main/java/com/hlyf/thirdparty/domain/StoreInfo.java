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
public class StoreInfo {
    private String result;
    private String virtualshopid;
    private String virtualshopName;
    private String o2ochannelId;
    private String o2ochannelName;

    private String latitude;
    private String longitude;
    private String kfphone;
    private String shipping_fee;
    private String shipping_time;

    private String open_level;
    private String is_online;
    private String third_tag_name;

    private String appid;
    private String appSecret;


}
