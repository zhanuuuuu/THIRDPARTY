package com.hlyf.thirdparty.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-02-28.
 */
public class test {
    public static void main(String[] args) {

        String str="{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"app_id\": 4115,\n" +
                "      \"app_poi_code\": \"t_Esdt1A3zOh\",\n" +
                "      \"name\": \"t_Esdt1A3zOh\",\n" +
                "      \"address\": \"南极洲04号站\",\n" +
                "      \"latitude\": 29388596,\n" +
                "      \"longitude\": 95397276,\n" +
                "      \"pic_url\": \"http://p1.meituan.net/160.120.90/crm/__37375183__1582979.png\",\n" +
                "      \"phone\": \"4009208801\",\n" +
                "      \"standby_tel\": \"4009208801\",\n" +
                "      \"shipping_fee\": 0.01,\n" +
                "      \"shipping_time\": \"06:00-23:00\",\n" +
                "      \"promotion_info\": \"这是测试门店\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"open_level\": 3,\n" +
                "      \"is_online\": 1,\n" +
                "      \"invoice_support\": 1,\n" +
                "      \"invoice_min_price\": 0,\n" +
                "      \"invoice_description\": \"\",\n" +
                "      \"city_id\": 999999,\n" +
                "      \"location_id\": 0,\n" +
                "      \"ctime\": 1497587322,\n" +
                "      \"utime\": 1563304511,\n" +
                "      \"third_tag_name\": null,\n" +
                "      \"tag_name\": \"蔬菜\",\n" +
                "      \"settlement_poi_id\": null,\n" +
                "      \"app_brand_code\": null,\n" +
                "      \"pre_book\": 0,\n" +
                "      \"time_select\": 1,\n" +
                "      \"pre_book_min_days\": 0,\n" +
                "      \"pre_book_max_days\": 0,\n" +
                "      \"pic_url_large\": null,\n" +
                "      \"mt_type_id\": null\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        try{
            JSONObject jsonObject=JSONObject.parseObject(str);
            if(jsonObject.containsKey("data") && !jsonObject.containsKey("error")){
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                if(jsonArray.size()>0){
                    System.out.println(jsonArray.getJSONObject(0).get("pic_url_large"));
                    Map<String,Object> maprequest = JSON.parseObject(jsonArray.getJSONObject(0).toJSONString(),Map.class);
                    Map<String,String> new_map_String = new HashMap();
                    for(Object key:maprequest.keySet()){
                        //new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                        System.out.println(key+" : "+String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                    }


                    String s1=JSON.toJSONString(maprequest, SerializerFeature.WriteNullListAsEmpty,
                            SerializerFeature.WriteNullStringAsEmpty,
                            SerializerFeature.WriteNullBooleanAsFalse,
                            SerializerFeature.WriteMapNullValue,
                            SerializerFeature.PrettyFormat);
                    System.out.println(""+s1);
                }
            }
            System.out.println(""+null);
            System.out.println(String.valueOf("").equals(""));


        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
