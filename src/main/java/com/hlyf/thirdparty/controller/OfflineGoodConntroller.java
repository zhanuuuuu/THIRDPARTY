package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtGoodService;
import com.hlyf.thirdparty.service.OfflineGoodService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019-07-11.
 * 线下商品操作都在这里  小程序目前商品操作也在这里
 */
@RestController
@Slf4j
@RequestMapping("/goodsoffline")
@Api(value = "API - MtGoodConntroller", description = "美团商品类API  线下操作 (或者小程序操作) ")
public class OfflineGoodConntroller {

    @Autowired
    private OfflineGoodService offlineGoodService;


    @ApiOperation(value="获取线下商品（模糊匹配）", notes="获取线下商品(模糊匹配)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"SearchVirtualShopGoods\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"pageindex\": \"页码，从1开始\",\n" +
                    "\t\"pagenum\": \"页面显示数量\",\n" +
                    "\t\"goodsName\": \"商品名称\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/searchvirtualshopgoods", method = RequestMethod.POST)
    @ResponseBody
    public  String searchvirtualshopgoods(@RequestParam(value = "jsondata",required = true) String jsondata,
                                               HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("查询商品 模糊匹配");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.offlineGoodService.SearchVirtualShopGoodsS(jsondata);
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="商品调价 （同步到美团）", notes="商品调价 （同步到美团）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"CheckVirtualShopGoodsPrice\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"虚拟门店编号\",\n" +
                    "\t\"phone\": \"手机号\",\n" +
                    "\t\"newPrice\": 需要调整的价格是多少 float类型,\n" +
                    "\t\"userType\": 传数字:1:门店,2:总部,\n" +
                    "\t\"sqltextback\": \"changeStoreGoodsPrice\",\n" +
                    "\t\"sku_id\": \"商品在美团的sku_id\",\n" +
                    "\t\"GoodsId\": \"需要调价商品编号\"\n" +

                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/CheckVirtualShopGoodsPrice", method = RequestMethod.POST)
    @ResponseBody
    public  String CheckVirtualShopGoodsPrice(@RequestParam(value = "jsondata",required = true) String jsondata,
                                          HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("商品改价");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.offlineGoodService.CheckVirtualShopGoodsPrice(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/price",
                    "POST");
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }


    @ApiOperation(value="创建商品 （同步到美团）", notes="创建商品 （同步到美团）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"AddVirtualShopGoods\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"goodsid\": \"\",\n" +
                    "\t\"Name\": \"\",\n" +
                    "\t\"Barcode\": \"\",\n" +
                    "\t\"IsFresh\": \"是否生鲜 （0：否，1：是）\",\n" +
                    "\t\"IsPacked\": \"是否打包卖（0：否，1：是）\",\n" +
                    "\t\"spec\": \"\",\n" +
                    "\t\"Unit\": \"\",\n" +
                    "\t\"ImageUrl\": \"\",\n" +
                    "\t\"newPrice\": \"\",\n" +
                    "\t\"min_order_count\": \"一个订单中此商品的最小购买量\",\n" +
                    "\t\"box_num\": \"打包盒数量\",\n" +
                    "\t\"box_price\": \"打包盒单价\",\n" +
                    "\t\"skus\": [{\n" +
                    "\t\t\"sku_id\": \"0101\",\n" +
                    "\t\t\"spec\": \"1000W\",\n" +
                    "\t\t\"price\": \"55\",\n" +
                    "\t\t\"stock\": \"10\",\n" +
                    "\t\t\"box_num\": \"1\",\n" +
                    "\t\t\"box_price\": \"0\"\n" +
                    "\t}],\n" +
                    "\t\"onStatus\": \"是否允许上下架 0:允许，1只允许上架，2只允许下架\",\n" +
                    "\t\"virtualshopid\": \"\",\n" +
                    "\t\"Description\": \"\",\n" +
                    "\t\"goodsGroupId\": \"分类id\",\n" +
                    "\t\"is_sold_out\": \"商品上下架状态，字段取值范围：0-上架，1-下架。\",\n" +
                    "\t\"phone\": \"\",\n" +
                    "\t\"sku_id\": \"\",\n" +
                    "\t\"stock\": \"\",\n" +
                    "\t\"sqltextback\": \"changeVirtualShopGoodsStatus\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/AddVirtualShopGoods", method = RequestMethod.POST)
    @ResponseBody
    public  String AddVirtualShopGoods(@RequestParam(value = "jsondata",required = true) String jsondata,
                                              HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("创建商品 模糊匹配");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
                System.out.println("key为："+key+"  值为："+String.valueOf(maprequest.get(key)));
            }
            result=this.offlineGoodService.AddVirtualShopGoods(new_map_String,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/initdata",
                    "POST");
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }


    @ApiOperation(value="创建商品分类 （同步到美团）", notes="创建商品分类 （同步到美团）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"AddGoodsCategory\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_code\": \"门店编号\",\n" +
                    "\t\"category_code\": \"原始的商品分类id\",\n" +
                    "\t\"category_name\": \"商品分类名称：(1)限定长度不超过8个字符\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/AddGoodsCategory", method = RequestMethod.POST)
    @ResponseBody
    public  String AddGoodsCategory(@RequestParam(value = "jsondata",required = true) String jsondata,
                                              HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("创建商品类别");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.offlineGoodService.AddGoodsCategory(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retailCat/update",
                    "POST");
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="得到商品分类 （线下）", notes="得到商品分类 （线下）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"GetGoodsCategories\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/offline/poi/GetGoodsCategories", method = RequestMethod.POST)
    @ResponseBody
    public  String GetGoodsCategories(@RequestParam(value = "jsondata",required = true) String jsondata,
                                    HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("查询商品 模糊匹配");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.offlineGoodService.GetGoodsCategories(maprequest,jsondata,
                    "",
                    "");
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="更改商品库存 （同步到美团）", notes="更改商品库存 （同步到美团）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"ChangeVirtualShopGoodsStock\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"虚拟门店标号\",\n" +
                    "\t\"goodsId\": \"商品id\",\n" +
                    "\t\"stock\": \"库存\",\n" +
                    "\t\"sku_id\": \"sku_id需要上传\",\n" +
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
    @RequestMapping(value = "api/offline/poi/ChangeVirtualShopGoodsStock", method = RequestMethod.POST)
    @ResponseBody
    public  String ChangeVirtualShopGoodsStock(@RequestParam(value = "jsondata",required = true) String jsondata,
                                    HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("更改商品库存");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.offlineGoodService.ChangeVirtualShopGoodsStock(maprequest,jsondata,
                    "https://waimaiopen.meituan.com/api/v1/retail/sku/stock",
                    "POST");
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

}
