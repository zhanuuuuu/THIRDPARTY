package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtService;
import com.hlyf.thirdparty.service.MtStoreService;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-05.
 */
@RestController
@Slf4j
@RequestMapping("/store")
@Api(value = "API - MtStoreConntroller", description = "美团门店类接口操作文档 ")
public class MtStoreConntroller {
    @Autowired
    private MtService mtService;

    @Autowired
    private MtStoreService mtStoreService;

    @ApiOperation(value="美团创建或更新门店信息", notes="美团创建或更新门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"createStore\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\",\n" +
                    " \"app_poi_code\": \"\",\n" +
                    " \"name\": \"\",\n" +
                    " \"address\": \"\",\n" +
                    " \"latitude\": \"\",\n" +
                    " \"longitude\": \"\",\n" +
                    " \"pic_url\": \"\",\n" +
                    " \"pic_url_large\": \"\",\n" +
                    " \"phone\": \"\",\n" +
                    " \"standby_tel\": \"\",\n" +
                    " \"shipping_fee\": \"1\",\n" +
                    " \"shipping_time\": \"7:00-9:00,11:30-19:00\",\n" +
                    " \"promotion_info\": \"\",\n" +
                    " \"open_level\": \"3\",\n" +
                    " \"is_online\": \"0\",\n" +
                    " \"invoice_support\": \"\",\n" +
                    " \"invoice_min_price\": \"\",\n" +
                    " \"invoice_description\": \"\",\n" +
                    " \"third_tag_name\": \"001\",\n" +
                    " \"pre_book\":\"\",\n" +
                    " \"time_select\":\"\",\n" +
                    " \"app_brand_code\":\"\"\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/save", method = RequestMethod.POST)
    @ResponseBody
    public  String poisave(@RequestParam(value = "jsondata",required = true) String jsondata,
                           HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("poisave");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.poisaveS(maprequest,jsondata);
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="美团门店设置为营业状态", notes="美团门店设置为营业状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/open", method = RequestMethod.POST)
    @ResponseBody
    public  String poiOpen(@RequestParam(value = "jsondata",required = true) String jsondata,
                           HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团门店设置为营业状态 poiOpen");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.poiOpenS(maprequest,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="美团门店设置为休息状态", notes="美团门店设置为休息状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/close", method = RequestMethod.POST)
    @ResponseBody
    public  String poiclose(@RequestParam(value = "jsondata",required = true) String jsondata,
                            HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团门店设置为休息状态 poiclose");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.poicloseS(maprequest,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="美团门店设置为下线状态", notes="美团门店设置为下线状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/offline", method = RequestMethod.POST)
    @ResponseBody
    public  String poioffline(@RequestParam(value = "jsondata",required = true) String jsondata,
                            HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团门店设置为休息状态 poioffline");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtService.poiStoreS(maprequest,jsondata,"https://waimaiopen.meituan.com/api/v1/poi/offline","POST");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="美团门店设置为上线状态", notes="美团门店设置为上线状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/online", method = RequestMethod.POST)
    @ResponseBody
    public  String poionline(@RequestParam(value = "jsondata",required = true) String jsondata,
                              HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团门店设置为上线状态 online");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtService.poiStoreS(maprequest,jsondata,"https://waimaiopen.meituan.com/api/v1/poi/online","POST");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="美团更新门店营业时间", notes="美团更新门店营业时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\",\n" +
                    "\t\"shipping_time\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/shippingtime/update", method = RequestMethod.POST)
    @ResponseBody
    public  String poishippingtimeupdate(@RequestParam(value = "jsondata",required = true) String jsondata,
                             HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("更新门店营业时间 shippingtime/update");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtService.poiStoreS(maprequest,jsondata,"https://waimaiopen.meituan.com/api/v1/poi/shippingtime/update","POST");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="美团设置门店延迟发配送时间", notes="美团设置门店延迟发配送时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"createStore\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"\",\n" +
                    "\t\"delay_seconds\": \"\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/logistics/setDelayPush", method = RequestMethod.POST)
    @ResponseBody
    public  String poilogisticssetDelayPush(@RequestParam(value = "jsondata",required = true) String jsondata,
                                         HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团设置门店延迟发配送时间 logistics/setDelayPush");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtService.poiStoreS(maprequest,jsondata,"https://waimaiopen.meituan.com/api/v1/poi/logistics/setDelayPush","POST");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }



    @ApiOperation(value="获取线下门店", notes="获取线下门店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"getStoreInfo\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"虚拟门店标号\",\n" +
                    "\t\"phone\": \"手机号\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/getstoreinfo", method = RequestMethod.POST)
    @ResponseBody
    public  String offlinePoiGetStoreS(@RequestParam(value = "jsondata",required = true) String jsondata,
                                            HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("获取线下门店");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.mtStoreService.GetStoreInfoS(jsondata);
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

}
