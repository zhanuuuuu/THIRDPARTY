package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-11.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class SearchGoods {
    private String  result;
    private String  id;
    private String  Name;
    private String  Barcode;
    private String  IsFresh;
    private String  IsPacked;
    private String  spec;
    private String  Unit;
    private String  ImageUrl;
    private String  price;
    private String  min_order_count;
    private String  box_num;
    private String  box_price;
}
