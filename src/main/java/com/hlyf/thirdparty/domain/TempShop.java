package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-18.
 * 	-- 	小程序总部 查询临时门店seltempShop
 -- 	in:(
 -- )
 -- 	out:(result：修改成功返回1，修改失败返回0 类别编号或类别名称重复
 -- 	)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class TempShop {

    private String  id;
    private String  storename;
    private String  city;
    private String  address;
    private String  maintype;
    private String  nomaintype;
    private String  phone;
    private String  person;
    private String  personphone;
    private String  passwordphone;
    private String  worktime;
    private String  isnoworktimeorder;
    private String  vreserveorderday;
    private String  isbill;
    private String  isdiscount;
    private String  agreementvalidity;
    private String  vBond;
    private String  vdistributionmode;
    private String  divideratio;
    private String  vBottom;
    private String  discountpreferentialsharing;
    private String  discountbottom;
    private String  beginningdeliveryprice;
    private String  deliveryprice;
    private String  manager;
    private String  ystorename;
    private String  yname;
    private String  yperson;
    private String  ynum;
    private String  yaddress;
    private String  yregdate;
    private String  ycheckunit;
    private String  yvaliddate;
    private String  ycheckdate;

}
