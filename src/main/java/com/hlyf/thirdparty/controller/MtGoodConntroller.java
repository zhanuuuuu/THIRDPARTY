package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtGoodService;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-06.
 */
@RestController
@Slf4j
@RequestMapping("/goods")
@Api(value = "API - MtGoodConntroller", description = "美团商品类API ")
public class MtGoodConntroller {

    @Autowired
    private MtGoodService mtGoodService;

    @ApiOperation(value="retailCat/update 创建/更新商品分类", notes="retailCat/update 创建/更新商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "参考美团官方文档 （retailCat/update 创建/更新商品分类）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retailCat/update", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsUpdateOrCreate(@RequestParam(value = "jsondata",required = true) String jsondata,
                                         HttpServletRequest request){
        String result="";

        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"创建/更新商品分类 api/v1/retailCat/update");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,"https://waimaiopen.meituan.com/api/v1/retailCat/update","POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retailCat/update :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retailCat/delete 删除商品分类", notes="retailCat/delete 删除商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "参考美团官方文档 （retailCat/delete 删除商品分类）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retailCat/delete", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsdelete(@RequestParam(value = "jsondata",required = true) String jsondata,
                                       HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"删除商品分类 api/v1/retailCat/delete");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retailCat/delete",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retailCat/delete :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/initdata 创建/更新商品[支持商品多规格,不含删除逻辑]", notes="retail/initdata 创建/更新商品[支持商品多规格,不含删除逻辑]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "参考美团官方文档 （retail/initdata 创建/更新商品[支持商品多规格,不含删除逻辑]）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/initdata", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsinitdata(@RequestParam(value = "jsondata",required = true) String jsondata,
                               HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"创建/更新商品[支持商品多规格,不含删除逻辑] api/v1/retail/initdata");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/initdata",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retail/initdata :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="retail/batchinitdata 批量创建/更新商品[支持商品多规格,不含删除逻辑]",
            notes="retail/batchinitdata 批量创建/更新商品[支持商品多规格,不含删除逻辑]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/batchinitdata 批量创建/更新商品[支持商品多规格,不含删除逻辑]）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/batchinitdata", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsBatchinitdata(@RequestParam(value = "jsondata",required = true) String jsondata,
                                 HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"批量创建/更新商品[支持商品多规格,不含删除逻辑] retail/batchinitdata ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/batchinitdata",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retail/initdata :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/sku/price 批量更新SKU价格",
            notes="retail/sku/price 批量更新SKU价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/sku/price 批量更新SKU价格）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/price", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsSkuPrice(@RequestParam(value = "jsondata",required = true) String jsondata,
                                      HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"批量更新SKU价格 retail/sku/price");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/price",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retail/initdata :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="retail/sku/stock 批量更新SKU库存",
            notes="retail/sku/stock 批量更新SKU库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/sku/stock 批量更新SKU库存）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/stock", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsSkuStock(@RequestParam(value = "jsondata",required = true) String jsondata,
                                 HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"批量更新SKU库存 retail/sku/price");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/stock",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/v1/retail/initdata :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/sku/save 创建/更新SKU信息",
            notes="retail/sku/save 创建/更新SKU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/sku/save 创建/更新SKU信息）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/save", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsSkusave(@RequestParam(value = "jsondata",required = true) String jsondata,
                                 HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"创建/更新SKU信息 retail/sku/save ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/save",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/sku/save :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/sku/sellStatus 批量更新售卖状态 上架（线下）",
            notes="retail/sku/sellStatus 批量更新售卖状态 上架 （线下）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "{\n" +
                            "\t\"sqltext\": \"CheckSetOnlineVirtualShopGoods\",\n" +
                            "\t\"appId\": \"4115\",\n" +
                            "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                            "\t\"O2OChannelId\": \"1\",\n" +
                            "\t\"virtualshopid\": \"线下\",\n" +
                            "\t\"phone\": \"线下\",\n" +
                            "\t\"goodsId\": \"线下\",\n" +
                            "\t\"app_poi_code\": \"美团\",\n" +
                            "\t\"food_data\": \"美团\",\n" +
                            "\t\"sell_status\": \"美团\",\n" +
                            "\t\"sqltextback\": \"SetOnlineVirtualShopGoods\"\n" +
                            "}",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/sellStatusonline", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsSkusellStatusonline(@RequestParam(value = "jsondata",required = true) String jsondata,
                                HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"retail/sku/sellStatus 批量更新售卖状态 ");

            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
                System.out.println("key为："+key+"  值为："+String.valueOf(maprequest.get(key)));
            }
            result=mtGoodService.GoodsSkusellStatusS(new_map_String,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/sellStatus",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/sku/sellStatus :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="retail/sku/sellStatus 批量更新售卖状态 下架（线下）",
            notes="retail/sku/sellStatus 批量更新售卖状态 下架（线下）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "{\n" +
                            "\t\"sqltext\": \"CheckSetOfflineVirtualShopGoods\",\n" +
                            "\t\"appId\": \"4115\",\n" +
                            "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                            "\t\"O2OChannelId\": \"1\",\n" +
                            "\t\"virtualshopid\": \"线下\",\n" +
                            "\t\"phone\": \"线下\",\n" +
                            "\t\"goodsId\": \"线下\",\n" +
                            "\t\"app_poi_code\": \"美团\",\n" +
                            "\t\"food_data\": \"美团\",\n" +
                            "\t\"sell_status\": \"美团\",\n" +
                            "\t\"sqltextback\": \"SetOfflineVirtualShopGoods\"\n" +
                            "}",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/sellStatusoffline", method = RequestMethod.POST)
    @ResponseBody
    public  String GoodsSkusellStatusoffline(@RequestParam(value = "jsondata",required = true) String jsondata,
                                            HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"retail/sku/sellStatus 批量更新售卖状态 下架 ");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
                System.out.println("key为："+key+"  值为："+String.valueOf(maprequest.get(key)));
            }
            result=mtGoodService.GoodsSkusellStatusofflineS(new_map_String,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/sellStatus",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 下架 retail/sku/sellStatus :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="retail/delete 删除商品",
            notes="retail/delete 删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/delete 删除商品）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/delete", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsretaildelete(@RequestParam(value = "jsondata",required = true) String jsondata,
                                HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"删除商品 retail/delete ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/delete",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/delete  :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="retail/bind/property 批量绑定商品属性",
            notes="retail/bind/property 批量绑定商品属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/bind/property 批量绑定商品属性）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/bind/property", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsbindproperty(@RequestParam(value = "jsondata",required = true) String jsondata,
                                     HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 批量绑定商品属性 retail/bind/property");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/bind/property",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/bind/property :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/sku/delete 删除SKU信息",
            notes="retail/sku/delete 删除SKU信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/sku/delete 删除SKU信息）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/sku/delete", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsapivretailskudelete(@RequestParam(value = "jsondata",required = true) String jsondata,
                                     HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 删除SKU信息 retail/sku/delete");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/delete",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/sku/delete :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retailCat/batchdelete/catandretail 批量删除商品分类及商品",
            notes="retailCat/batchdelete/catandretail 批量删除商品分类及商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retailCat/batchdelete/catandretail 批量删除商品分类及商品）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retailCat/batchdelete/catandretail", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsapiv1retailCatbatchdeletecatandretail(@RequestParam(value = "jsondata",required = true) String jsondata,
                                            HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 批量删除商品分类及商品 retailCat/batchdelete/catandretail");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retailCat/batchdelete/catandretail",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retailCat/batchdelete/catandretail :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/updateAppFoodCodeByOrigin 根据原商品编码更换新商品编码",
            notes="retail/updateAppFoodCodeByOrigin 根据原商品编码更换新商品编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/updateAppFoodCodeByOrigin 根据原商品编码更换新商品编码）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/updateAppFoodCodeByOrigin", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsapiv1retailupdateAppFoodCodeByOrigin(@RequestParam(value = "jsondata",required = true) String jsondata,
                                                              HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 根据原商品编码更换新商品编码 retail/updateAppFoodCodeByOrigin ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/updateAppFoodCodeByOrigin",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/updateAppFoodCodeByOrigin  :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="retail/updateAppFoodCodeByNameAndSpec 根据商品名称和规格名称更换新的商品编码",
            notes="retail/updateAppFoodCodeByNameAndSpec 根据商品名称和规格名称更换新的商品编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata",
                    value = "参考美团官方文档 （retail/updateAppFoodCodeByNameAndSpec 根据商品名称和规格名称更换新的商品编码）",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/retail/updateAppFoodCodeByNameAndSpec", method = RequestMethod.POST)
    @ResponseBody
    public  String Goodsapiv1retailupdateAppFoodCodeByNameAndSpec(@RequestParam(value = "jsondata",required = true) String jsondata,
                                                             HttpServletRequest request){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 根据商品名称和规格名称更换新的商品编码 retail/updateAppFoodCodeByNameAndSpec ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=mtGoodService.GoodsUpdateS(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/updateAppFoodCodeByNameAndSpec",
                    "POST");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 retail/updateAppFoodCodeByNameAndSpec :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }
}
