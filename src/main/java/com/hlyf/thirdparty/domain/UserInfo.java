package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-28.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class UserInfo {

    private String result;
    private String phone;
    private String name;
    private String virtualshopid;
    private String password;

}
