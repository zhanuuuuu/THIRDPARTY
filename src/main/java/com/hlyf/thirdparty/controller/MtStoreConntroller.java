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
import java.util.HashMap;
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


    @ApiOperation(value="美团创建门店 （线下）", notes="美团创建门店 （线下） " +
            "说明  ： " +
            "  小程序总部 创建临时门店CreateShop\n" +
            "-- \tin:(storename:门店名称，city：城市，address：地址，maintype:主营品类，nomaintype：辅营品类，kfphone:客服电话号码，person：商户联系人，personphone：商户联系人电话\n" +
            "-- passwordphone：接收商家账号密码手机号码，worktime：门店营业时间（格式09：00-23：59,00：00-04：00），isnoworktimeorder：是否支持非营业时间预下单，reserveorderday：提前几天预下单（天）\n" +
            "-- isbill：是否支持开发票，isdiscount：是否申请优惠，agreementvalidity:协议有效期，Bond：保证金（元），distributionmode：配送方式，美团配送或商家自配两种，divideratio:分成,\n" +
            "-- bottom：保底（元），\n" +
            "-- discountpreferentialsharing：优惠分成（%），discountbottom：优惠保底（元），beginningdeliveryprice：起送价（元），deliveryprice：配送费（元），manager：负责人MIS账号，\n" +
            "-- ystorename：营业执照门店名称，yname：营业执照名称，yperson：营业执照法人，ynum：营业执照编号及注册号，yaddress：营业执照地址，yregdate：注册/成立日期，\n" +
            "-- ycheckunit：发证/登记机关(填写右下角红章内文字) ，yvaliddate：营业执照有效期，ycheckdate：核准日期(填写右下角日期)\n" +
            "-- SettleMode:报价模式,O2ORate:O2O渠道方扣点数,ServiceRate:直连商家扣点数,InsuranceRate:保险扣点数,ProfitRate:利润率,FixedOffset:固定费用,MiddleRate:中台服务费\n" +
            "-- )" +
            "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"CreateShop\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\",\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/CreateShop", method = RequestMethod.POST)
    @ResponseBody
    public  String CreateShop(@RequestParam(value = "jsondata",required = true) String jsondata,
                           HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("CreateShop");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=mtService.CreateShopS(new_map_String,jsondata);
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="小程序总部 添加调价规则 AddVirtualShopGoodsPriceRule（线下）", notes="小程序总部 添加调价规则AddVirtualShopGoodsPriceRule （线下） " +
            "说明  ： " +
            "  in:(virtualshopid:门店编号 GoodsId:商品ID,GoodsGroupName:类别名称,LowerRate:允许下调率（大于0小于1格式0.2）,LowerRate:允许上浮率（大于0小于1格式0.2），\n" +
            "-- StartTime：规则开始时间,EndTime：规则结束时间\n" +
            "-- )")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"AddVirtualShopGoodsPriceRule\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\",\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/AddVirtualShopGoodsPriceRule", method = RequestMethod.POST)
    @ResponseBody
    public  String AddVirtualShopGoodsPriceRule(@RequestParam(value = "jsondata",required = true) String jsondata,
                              HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("CreateShop");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=mtService.AddVirtualShopGoodsPriceRuleS(new_map_String,jsondata);
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


    @ApiOperation(value="小程序总部 查询临时门店seltempShop (线下)", notes="小程序总部 查询临时门店seltempShop （线下）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"seltempShop\",\n" +
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
    @RequestMapping(value = "api/v1/poi/seltempShop", method = RequestMethod.POST)
    @ResponseBody
    public  String seltempShop(@RequestParam(value = "jsondata",required = true) String jsondata,
                           HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("seltempShop");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.seltempShopS(maprequest,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 查询调价规则 GetVirtualShopGoodsPriceRules (线下)", notes="小程序总部 查询调价规则 GetVirtualShopGoodsPriceRules （线下）" +

            "in:(Id:规则ID,virtualshopid:门店编号 GoodsId:商品ID,GoodsGroupName:类别名称,LowerRate:允许下调率（大于0小于1格式0.2）,LowerRate:允许上浮率（大于0小于1格式0.2），\n" +
            "-- StartTime：规则开始时间,EndTime：规则结束时间\n" +
            "-- )\n" +
            "-- out:(result：天机成功返回1，添加失败返回0 \t")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"GetVirtualShopGoodsPriceRules\",\n" +
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
    @RequestMapping(value = "api/v1/poi/GetVirtualShopGoodsPriceRules", method = RequestMethod.POST)
    @ResponseBody
    public  String GetVirtualShopGoodsPriceRules(@RequestParam(value = "jsondata",required = true) String jsondata,
                               HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("seltempShop");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.GetVirtualShopGoodsPriceRulesS(maprequest,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 获取门店信息准备修改 GetVirtualShop (线下)", notes="" +
            "小程序总部 获取门店信息准备修改GetVirtualShop （线下）" +
            "-- \tin:(virtualshopName:可以传门店ID，门店名称模糊查询，传空显示所有门店)\n" +
            "-- out:(result：查询成功返回1，查询失败返回0 \tvirtualshopid:门店编号 virtualshopName:门店名称 o2ochannelId:渠道编号\to2ochannelName:渠道名称\n" +
            "-- longitude:经度\t\tlatitude:纬度 kfphone:客服电话 shipping_fee:订单配送费 shipping_time:门店营业时间 open_level:门店的营业状态：1为可配送，3为休息中\n" +
            "-- is_online:门店上下线状态：1为上线，0为下线  third_tag_name:门店品类 例如：火锅，特色菜，地方菜 appid:appid appSecret:appSecret\n" +
            "-- SettleMode:报价模式,O2ORate:O2O渠道方扣点数,ServiceRate:直连商家扣点数,InsuranceRate:保险扣点数,ProfitRate:利润率,FixedOffset:固定费用,MiddleRate:中台服务费" +
            "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"GetVirtualShop\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"virtualshopName\": \"可以传门店ID，门店名称模糊查询，传空显示所有门店\",\n" +
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
    @RequestMapping(value = "api/v1/poi/GetVirtualShop", method = RequestMethod.POST)
    @ResponseBody
    public  String GetVirtualShop(@RequestParam(value = "jsondata",required = true) String jsondata,
                               HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("GetVirtualShop");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=mtService.GetVirtualShopS(maprequest,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 修改门店 EditVirtualShop (线下)", notes="" +
            "小程序总部 修改门店 EditVirtualShop (线下)" +
            "in:(virtualshopid:门店编号 name:门店名称\n" +
            "-- longitude:经度\t\tlatitude:纬度 kfphone:客服电话 shipping_fee:订单配送费 shipping_time:门店营业时间 open_level:门店的营业状态：1为可配送，3为休息中\n" +
            "-- is_online:门店上下线状态：1为上线，0为下线  third_tag_name:门店品类 例如：火锅，特色菜，地方菜 \n" +
            "-- invoice_support:是否支持发票，invoice_min_price:门店支持开发票的最小订单价,invoice_description:发票相关说明,pre_book:是否支持营业时间范围外预下单(1：支持，0：不支持),time_select:是否支持营业时间范围内预下单(1：支持，0：不支持)\n" +
            "-- SettleMode:报价模式,O2ORate:O2O渠道方扣点数,ServiceRate:直连商家扣点数,InsuranceRate:保险扣点数,ProfitRate:利润率,FixedOffset:固定费用,MiddleRate:中台服务费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"EditVirtualShop\",\n" +
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
    @RequestMapping(value = "api/v1/poi/EditVirtualShop", method = RequestMethod.POST)
    @ResponseBody
    public  String EditVirtualShop(@RequestParam(value = "jsondata",required = true) String jsondata,
                                  HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("EditVirtualShop");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=mtService.EditVirtualShopS(new_map_String,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 删除调价规则 DeleteVirtualShopGoodsPriceRule (线下)", notes="" +
            "in:(virtualshopid:门店编号 GoodsId:商品ID,GoodsGroupName:类别名称,LowerRate:允许下调率（大于0小于1格式0.2）,LowerRate:允许上浮率（大于0小于1格式0.2），\n" +
            "-- StartTime：规则开始时间,EndTime：规则结束时间\n" +
            "-- )\n" +
            "-- out:(result：天机成功返回1，添加失败返回0 \t\n" +
            "-- )")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"DeleteVirtualShopGoodsPriceRule\",\n" +
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
    @RequestMapping(value = "api/v1/poi/DeleteVirtualShopGoodsPriceRule", method = RequestMethod.POST)
    @ResponseBody
    public  String DeleteVirtualShopGoodsPriceRule(@RequestParam(value = "jsondata",required = true) String jsondata,
                                   HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("EditVirtualShop");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=mtService.DeleteVirtualShopGoodsPriceRuleS(new_map_String,jsondata);
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
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



    @ApiOperation(value="获取线下门店 (线下)", notes="获取线下门店 (线下)")
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
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="小程序门店总部 模糊查询门店信息 getLikeStoreInfo (线下)", notes="小程序门店总部 模糊查询门店信息getLikeStoreInfo (线下)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"getStoreInfo\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"VirtualShopName\": \"门店名称\",\n" +
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
    @RequestMapping(value = "api/offline/poi/getLikeStoreInfo", method = RequestMethod.POST)
    @ResponseBody
    public  String getLikeStoreInfo(@RequestParam(value = "jsondata",required = true) String jsondata,
                                       HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("获取线下门店");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.mtStoreService.getLikeStoreInfoS(jsondata);
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    @ApiOperation(value="获取线下门店商品 (线下)", notes="获取线下门店商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"getStoreGoodsInfo\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"virtualshopid\": \"虚拟门店标号\",\n" +
                    "\t\"GoodsGroupId\": \"GoodsGroupId\",\n" +
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
    @RequestMapping(value = "api/offline/poi/getstoregoodsinfo", method = RequestMethod.POST)
    @ResponseBody
    public  String offlinePoigetStoreGoodsInfo(@RequestParam(value = "jsondata",required = true) String jsondata,
                                       HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("获取线下门店商品");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }
            result=this.mtStoreService.GetStoreGoodsInfoS(jsondata);
        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), ""));
        }
        return result;
    }

    //线下商品同步
    @ApiOperation(value="小程序总部 点击同步门店 SyncShop （线下）", notes="小程序总部 点击同步门店 线下  示例：" +
            "{ \t\"sqltext\": \"SyncShop\", " +
            "\t\"appId\": \"4115\", " +
            "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\"," +
            " \t\"O2OChannelId\": \"1\", " +
            "\t\"app_poi_codes\": \"t_Esdt1A3zOh\" }" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"SyncShop\",\n" +
                    "\t\"appId\": \"4115\",\n" +
                    "\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    "\t\"O2OChannelId\": \"1\",\n" +
                    "\t\"app_poi_codes\": \"美团门店号 APP方门店id(半角逗号分隔)\"\n" +
                    "}",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/poi/SyncShop", method = RequestMethod.POST)
    @ResponseBody
    public  String poiSyncShop(@RequestParam(value = "jsondata",required = true) String jsondata,
                            HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("美团门店设置为休息状态 poiclose");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
                System.out.println("key为："+key+"  值为："+String.valueOf(maprequest.get(key)));
            }
            result=mtStoreService.SyncShop(new_map_String,jsondata,"https://waimaiopen.meituan.com/api/v1/poi/mget","GET");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

}
