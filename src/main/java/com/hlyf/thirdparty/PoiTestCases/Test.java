package com.hlyf.thirdparty.PoiTestCases;

import com.sankuai.meituan.waimai.opensdk.constants.ErrorEnum;
import com.sankuai.meituan.waimai.opensdk.constants.PoiQualificationEnum;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import com.sankuai.meituan.waimai.opensdk.factory.APIFactory;
import com.sankuai.meituan.waimai.opensdk.util.SignGenerator;
import com.sankuai.meituan.waimai.opensdk.vo.PoiParam;
import com.sankuai.meituan.waimai.opensdk.vo.SystemParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zhangyuanbo02 on 15/12/9.
 */
public class Test {

    private final static SystemParam sysPram = new SystemParam("2820", "b0b1c772b3228fdd83c7ae797e7fbb44");
    //    private final static String appPoiCode = "ceshi_POI_II";
    private final static String appPoiCode = "ceshi_02";

    private final static String abc="app_poi_code\tstring\t是\t25381\tAPP方门店id，即商家中台系统里门店的编码。如商家在操作绑定门店至开放平台应用中时，未绑定三方门店id信息，则默认APP方门店id与美团门店id相同。\n" +
            "category_code_origin\tstring\t与category_name_origin至多填写一个\tA01\t原始的商品分类id：(1)与category_name_origin字段至多填一个；(2)更新分类时可使用此字段。\n" +
            "category_name_origin\tstring\t与category_code_origin至多填写一个\t日用品\t原始的商品分类名称：(1)与category_code_origin字段至多填一个；(2)创建一级分类时，也包括同时创建二级分类的情况，此字段不传；(3)更新一级分类时，此字段传原始的一级分类名称；(4)在店内已有的一级分类下创建二级分类时，此字段传一级分类名称；(5)更新二级分类时，此字段传原始的二级分类名称。\n" +
            "category_code\tstring\t否\tA01\t商品分类id：(1)创建一级分类时如商家有分类id信息，可传入此字段；(2)更新分类时，此字段若不传，原分类id则置空。(3)门店内一级、二级分类id都不允许重复。\n" +
            "category_name\tstring\t是\t日用百货\t商品分类名称：(1)限定长度不超过8个字符。(2)创建一级分类时，此字段传一级分类名称。(3)更新一级分类id时，此字段传原始的一级分类名称。(4)更新一级分类名称时，此字段传新的一级分类名称。(5)更新二级分类名称时，此字段传新的二级分类名称。\n" +
            "secondary_category_code\tstring\t否\tB01\t二级商品分类id：(1)创建二级分类时如商家有分类id信息，可传入此字段。(2)门店内一级、二级分类id都不允许重复。\n" +
            "secondary_category_name\tstring\t否\t家用电器\t二级商品分类名称，创建二级分类时，此字段传二级分类名称。\n" +
            "sequence\tint\t否\t1\t商品分类的排序：(1)数字越小，排位越靠前；(2)当前调用中如有二级分类信息，则此字段排序将作用于二级分类；(3)此字段若不传，则默认为值为0。\n" +
            "target_level\tint\t否\t2\t调整分类层级时的目标等级：(1)仅当category_name_origin或category_code_origin字段有值时才生效，即将原始的分类层级调整为此目标等级。(2)此字段取值范围：1-一级分类，2-二级分类；(3)当原始的分类为一级分类且其下有二级分类时，不允许将此一级分类调整为二级分类。\n" +
            "target_parent_name\tstring\t仅当target_level=2时填写\t电器\t调整为二级分类时所属的一级分类名称：(1)仅当category_name_origin或category_code_origin字段有值时才生效；(2)target_parent_name字段传店内已存在的一级分类名称；(3)target_parent_name将作为调整后二级分类的父级分类。\n" +
            "top_flag\tint\t否\t1\t是否开启置顶：(1)字段取值范围：0-否，1-是；(2)仅支持一级分类设置分时置顶。\n" +
            "weeks_time\tstring\t当top_flag=1时必填，且需要与period字段同时设置。\t1,3,4\t置顶周期：(1)1,2,3,4,5,6,7分别表示周一至周日；(2)多个日期之间用英文逗号分隔；(3)仅支持一级分类设置分时置顶。\n" +
            "period\tstring\t当top_flag=1时必填，且需要与weeks_time字段同时设置。\t00:00-09:00,11:30-16:30\t置顶时段：(1)最多支持设置5个时间段，多个时间段之间用英文逗号分隔；(2)若设置多个时间段，则前一个时间段的结束时间不能晚于后一个时间段的开始时间；(3)仅支持一级分类设置分时置顶。";
    public static void main(String[] args) throws ApiSysException {
        //poiGetIds();
        String paramValue = null;
        try {
            paramValue = URLDecoder.decode("open%E5%B9%B3%E5%8F%B0+%E8%AE%BE%E7%BD%AE%E4%BC%91%E6%81%AF%E7%8A%B6%E6%80%81 \'\'",
                    "UTF-8");
            System.out.println(paramValue);
            System.out.println("长度: "+paramValue.length());

            paramValue = URLDecoder.decode(paramValue,
                    "UTF-8");
            System.out.println(paramValue);
            System.out.println("长度: "+paramValue.length());

            paramValue = URLDecoder.decode(paramValue,
                    "UTF-8");
            System.out.println(paramValue);
            System.out.println("长度: "+paramValue.length());

            String urlForGenSig="http://148.70.87.251:8080/Thirdparty/meituan/api/getpush/storestateupdate?app_id=4115&app_poi_code=t_Esdt1A3zOh&operate_time=1561973501&operate_user=BUSINESS&poi_status=120&reason=open平台 设置休息状态&timestamp=1561973502f0b1b7d92d96485e704316604a24bd5a";

            String sigByMe = SignGenerator.genSig(urlForGenSig);
            System.out.println(sigByMe);

        } catch (UnsupportedEncodingException e) {
            paramValue=null;
            System.out.println("解码出错了");

            e.printStackTrace();
        }

        for(int i=0;i<10;i++){
            if(i==6){
                return;
            }
            System.out.println(i);
        }

       // throw new ApiSysException(ErrorEnum.LACK_OF_PARAM);
    }

    public static void poiSave() {
        PoiParam PoiPram = new PoiParam();
        PoiPram.setApp_poi_code("ceshi_poi1");
        PoiPram.setName("我的门店");
        PoiPram.setAddress("我的门店的地址");
        PoiPram.setLatitude(40.810249f);
        PoiPram.setLongitude(117.502289f);
        PoiPram.setPhone("13425355733");
        PoiPram.setShipping_fee(2f);
        PoiPram.setShipping_time("09:00-13:30,19:00-21:40");
        PoiPram.setPic_url("http://cdwuf.img46.wal8.com/img46/525101_20150811114016/144299728957.jpg");
        PoiPram.setOpen_level(1);
        PoiPram.setIs_online(1);
        PoiPram.setPre_book_min_days(1);
        PoiPram.setPre_book_max_days(2);
        PoiPram.setApp_brand_code("zhajisong");
        PoiPram.setThird_tag_name("麻辣烫");

        try {
            String result = APIFactory.getPoiAPI().poiSave(sysPram, PoiPram);
            System.out.println(result);
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiGetIds() {
        try {
            String result = APIFactory.getPoiAPI().poiGetIds(sysPram);
            System.out.println(result);
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiMget() {
        try {
            List<PoiParam> Poslist = APIFactory.getPoiAPI().poiMget(sysPram, "ceshi_poi1,ceshi_100");
            System.out.println(Poslist);
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiTagList() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiTagList(sysPram));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiOpen() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiOpen(sysPram, "ceshi_poi1"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiClose() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiClose(sysPram, "ceshi_poi1"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiOffline() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiOffline(sysPram, "ceshi_poi1", "缺货"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiOnline() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiOnline(sysPram, "ceshi_poi1"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiQualifySave() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiQualifySave(sysPram, "ceshi_poi1",
                    PoiQualificationEnum.BUSINESS_LICENSE,
                    "http://cdwuf.img46.wal8.com/img46/525101_20150811114016/14429972901.jpg",
                    "2016-01-01", "天安门", "11019123"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiSendTimeSave() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiSendTimeSave(sysPram, "ceshi_poi1,ceshi_100", 50));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiAdditionalSave() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiAdditionalSave(sysPram, "ceshi_poi1","cyinchao@sina.com", "zhajisong", "8888"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }

    public static void poiUpdatePromotionInfo() {
        try {
            System.out.println(APIFactory.getPoiAPI().poiUpdatePromotionInfo(sysPram, "ceshi_poi1", "这是一条用于测试的门店公告信息"));
        } catch (ApiOpException e) {
            e.printStackTrace();
        } catch (ApiSysException e) {
            e.printStackTrace();
        }
    }



//    public static void main(String[] args) {
//        byte[] imgData = {85,112,108,111,97,100,115,47,80,114,111,100,117,99,116,115,47,49,53,48,55,47,48,49,47,105,109,103,46,106,112,103};
//        try {
//            System.out.println(
//                APIFactory.getImageApi().imageUpload(systemParam, appPoiCode, imgData, "ceshi.jpg"));
//        } catch (ApiOpException e) {
//            e.printStackTrace();
//        } catch (ApiSysException e) {
//            e.printStackTrace();
//        }
//    }


}
