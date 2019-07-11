package com.hlyf.thirdparty.result;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hlyf.thirdparty.tool.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019-05-24.
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
@ToString

public class ResultMsg implements Serializable {

    private boolean success;
    private String code;

    private String message;

    private Object result;



    public static void GetTypeClass(String var0){
        if(var0.getClass().toString().equals("class java.lang.String")){

            if (var0!=null) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(var0);
                var0 = m.replaceAll("");
            }

            System.out.println(var0);
        } else {
            System.out.println("我不是字符串类型");
        }
    }

    public static void main(String[] args) {
        test2();


    }
    private static void test2() {
        ResultMsg resultMsg = new ResultMsg(true, "1001", "正确", (String) null);

        String jsonString = JSON.toJSONString(resultMsg, SerializerFeature.WriteMapNullValue);
        System.out.println(jsonString);
    }
    private static void test1() {
        ResultMsg resultMsg = new ResultMsg(true, "1001", "正确", (String) null);

        resultMsg = new ResultMsg(true, "1001", "正确", (ResultMsg) resultMsg);

        String jsonString = JSON.toJSONString(resultMsg);
        System.out.println(jsonString);
        resultMsg = new ResultMsg(true, "1001", "正确", (ResultMsg) resultMsg);

        jsonString = JSONObject.toJSONString(new ResultMsg(true, "1001", "正确", (ResultMsg) resultMsg));
        System.out.println(jsonString);


        JSONObject maleArray = JSONObject.parseObject(jsonString);

        for (Map.Entry<String, Object> entry : maleArray.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //第三种方式
        Map<String,Object> mapType = JSON.parseObject(jsonString,Map.class);
        System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
        for (Object obj : mapType.keySet()){
                System.out.println("key为："+obj+"值为："+mapType.get(obj));
        }
    }
}
