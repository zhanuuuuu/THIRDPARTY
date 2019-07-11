package com.hlyf.thirdparty.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-08.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class LoginResult {

   @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue})
   private String result;
   private String shopid;
   private String shopName;
   private String o2ochannelId;
   private String o2ochannelName;
}
