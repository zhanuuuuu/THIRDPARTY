package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MiniService;
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
 * Created by Administrator on 2019-07-06.
 * 小程序相关
 */
@RestController
@Slf4j
@RequestMapping("/mini")
@Api(value = "API - MiniProgramConntroller", description = "小程序相关接口封装 ")
public class MiniProgramConntroller {

    @Autowired
    private MiniService miniService;

    @ApiOperation(value="根据code获取openid", notes="根据code获取openid,或者获取unionid（如果用户有关注了该小程序同主体的公众号 ）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",
                    value = "code",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/jscode2session", method = RequestMethod.POST)
    @ResponseBody
    public  String jscode2session(@RequestParam(value = "code",required = true) String code,
                                 HttpServletRequest request){
        String result="";
        try {
            result=this.miniService.GetOpenIdByCode(code);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/sns/jscode2session :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_ERROR.getCode(),
                            GlobalEumn.MINIPROGRAM_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="根据code,appid，appsecret 获取openid", notes="根据code,appid，appsecret获取openid,或者获取unionid（如果用户有关注了该小程序同主体的公众号 ）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",
                    value = "code",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
            @ApiImplicitParam(name = "appid",
                    value = "appid",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
            @ApiImplicitParam(name = "appsecret",
                    value = "appsecret",
                    paramType ="query" ,required = true,dataType = "string",defaultValue = "")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/getOpenidOrUnionid", method = RequestMethod.POST)
    @ResponseBody
    public  String getOpenidOrUnionid(@RequestParam(value = "code",required = true) String code,
                                      @RequestParam(value = "appid",required = true) String appid,
                                      @RequestParam(value = "appsecret",required = true) String appsecret,
                                  HttpServletRequest request){
        String result="";
        try {
            result=this.miniService.GetOpenIOrUniuiddByCode(code,appid,appsecret);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 api/sns/jscode2session :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_ERROR.getCode(),
                            GlobalEumn.MINIPROGRAM_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="解密 根据session_key(通过上一步获取openid得到的,微信文档说五分钟过期),vi,encryptedData解密出来unionid或者用户手机号", notes="根据session_key,vi,encryptedData解密出来unionid或者用户手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "encryptedData", value = "encryptedData", paramType ="query" ,required = true,dataType = "string"),
            @ApiImplicitParam(name = "sessionKey", value = "sessionKey", paramType ="query" ,required = true,dataType = "string"),
            @ApiImplicitParam(name = "iv", value = "iv", paramType ="query" ,required = true,dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/decrypt", method = RequestMethod.POST)
    @ResponseBody
    public  String decrypt(@RequestParam(value = "encryptedData",required = true) String encryptedData,
                           @RequestParam(value = "sessionKey",required = true) String sessionKey,
                           @RequestParam(value = "iv",required = true) String iv){
        String result="";
        try {
            result=this.miniService.Wxdecrypt(encryptedData,sessionKey,iv);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 pi/wxuser/decrypt :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_DECRYPT.getCode(),
                            GlobalEumn.MINIPROGRAM_DECRYPT.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序登录Login", notes="小程序登录Login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "jsondata：示例{\n" +
                    " \"sqltext\": \"门店传loginStore 总部传loginAdmin\",  \n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\",\n" +
                    " \"openid\": \"\",\n" +
                    " \"unionid\": \"\",\n" +
                    " \"phone\": \"\",\n" +
                    " \"password\": \"\"\n" +
                    "} ", paramType ="query" ,required = true,dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/login", method = RequestMethod.POST)
    @ResponseBody
    public  String wxlogin(@RequestParam(value = "jsondata",required = true) String jsondata){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 小程序登录 ");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=miniService.WxLogin(maprequest,jsondata);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 pi/wxuser/wxlogin :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getCode(),
                            GlobalEumn.MINIPROGRAM_APPSECRET_ERROR.getMesssage(), ""));
        }
        return result;
    }




    @ApiOperation(value="小程序门店 点击门店获取门店信息 getStoreInfo", notes="小程序门店 点击门店获取门店信息getStoreInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "jsondata：示例{\n" +
                    "\t\"sqltext\": \"getStoreInfo\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"\",\n" +
                    "\t\"phone\": \"\"\n" +
                    "} ", paramType ="query" ,required = true,dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/getstoreinfo", method = RequestMethod.POST)
    @ResponseBody
    public  String wxgetStoreInfo(@RequestParam(value = "jsondata",required = true) String jsondata){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 小程序门店 点击门店获取门店信息");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=miniService.WxStoreInfo(maprequest,jsondata);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 pi/wxuser/getstoreinfo :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.SYSTEM_ERROR.getCode(),
                            GlobalEumn.SYSTEM_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="小程序门店 点击分类获取商品信息 getStoreGoodsInfo  （线下）", notes="" +
            "in:(virtualshopid:虚拟门店编号\tphone:手机号 GoodsGroupId:商品分类编号，goodsinfo:商品模糊查询信息)" +
            "（ 备注 ：" +
            "GoodsGroupId:商品分类编号，goodsinfo:商品模糊查询信息 只能上传一个）\n" +

            "-- out:(result：查询成功返回1，查询失败返回0 \tvirtualshopid:门店编号 virtualshopName:门店名称 o2ochannelId:渠道编号\to2ochannelName:渠道名称\n" +
            "-- goodsName:商品名称\t\tO2OCode:渠道方商品名称 GoodsId:商品ID description:商品描述 skus:美团SKU——json格式 ObtainedPrice:门店价格\n" +
            "-- CostPrice:成本价  min_order_count:一个订单的最小购买量\t\tunit:单位 spec:规格  box_num:单个商品需要打包盒数量 box_price:打包盒单价\n" +
            "-- GoodsGroupId:类别ID\t\tGoodsGroupName:类别名称 \tStatus:商品上下架状态 0-上架，1-下架\t\tImageUrl:图片路径 stock 库存" +
            ",onStatus:是否允许上下架 0:允许，1只允许上架，2只允许下架\n" +
            "-- Barcode:国际条码 IsFresh:是否生鲜 （0：否，1：是） ,IsPacked:是否打包卖（0：否，1：是）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "jsondata：示例{\n" +
                    "\t\"sqltext\": \"getStoreGoodsInfo\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"虚拟门店标号\",\n" +
                    "\t\"GoodsGroupId\": \"GoodsGroupId\",\n" +
                    "\t\"goodsinfo\": \"GoodsGroupId\",\n" +
                    "\t\"phone\": \"手机号\"\n" +
                    "}", paramType ="query" ,required = true,dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/wxuser/getstoregoodsinfo", method = RequestMethod.POST)
    @ResponseBody
    public  String wxgetgetStoreGoodsInfo(@RequestParam(value = "jsondata",required = true) String jsondata){
        String result="";
        try {
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+" 门店获取商品信息getStoreGoodsInfo");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=miniService.WxStoreGoodsInfo(maprequest,jsondata);
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName()+"出错了 pi/wxuser/getStoreGoodsInfo :{}",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.SYSTEM_ERROR.getCode(),
                            GlobalEumn.SYSTEM_ERROR.getMesssage(), ""));
        }
        return result;
    }
}
