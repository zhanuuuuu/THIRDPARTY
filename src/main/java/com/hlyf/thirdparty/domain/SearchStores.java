package com.hlyf.thirdparty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019-07-19.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString
public class SearchStores {

    private String  result;
    private String  virtualshopid;
    private String  virtualshopName;
    private String  o2ochannelId;
    private String  o2ochannelName;

}
