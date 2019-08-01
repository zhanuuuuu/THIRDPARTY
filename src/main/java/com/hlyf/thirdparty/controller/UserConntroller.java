package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.UserConntrollerService;
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
 * Created by Administrator on 2019-07-28.
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api(value = "API - UserConntroller", description = "员工管理类接口文档 ")
public class UserConntroller {

    @Autowired
    private UserConntrollerService userConntrollerService;


    @ApiOperation(value="小程序总部 新增员工 AddUser（线下）", notes="小程序总部 新增员工 AddUser（线下） " +
            "说明  ： " +
            "  in:(virtualshopid：门店编号 phone:电话号码 name:姓名 password:密码 data:[{virtualshopid:002},{}]) 备注：给出的示例只是公用参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"AddUser\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\"\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/user/AddUser", method = RequestMethod.POST)
    @ResponseBody
    public  String AddUser(@RequestParam(value = "jsondata",required = true) String jsondata,
                                                HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("AddUser");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=userConntrollerService.AddUserS(maprequest,jsondata);
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 修改员工信息 UpdateUserInfo（线下）", notes="小程序总部 修改员工信息 UpdateUserInfo （线下） " +
            "说明  ： " +
            "  in:(name:姓名，virtualshopid：门店编号，password:密码 phone:手机号 data:[{},{}]\n" +
            "-- ) 备注：给出的示例只是公用参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"UpdateUserInfo\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\"\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/user/UpdateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public  String UpdateUserInfo(@RequestParam(value = "jsondata",required = true) String jsondata,
                           HttpServletRequest request){
        String result="";
        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("UpdateUserInfo");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=userConntrollerService.UserCommUpdateUserInfo(maprequest,jsondata,"小程序总部 修改员工信息 UpdateUserInfo");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 启用/禁用员工 SetUserStatus（线下）", notes="小程序总部 启用/禁用员工 SetUserStatus （线下） " +
            "说明  ： " +
            "  in:(phone:手机号 flag:禁用传0，启用传1\n" +
            "-- )备注：给出的示例只是公用参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    " \"sqltext\":\"SetUserStatus\",\n" +
                    " \"appId\": \"4115\",\n" +
                    " \"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
                    " \"O2OChannelId\": \"1\"\n" +
                    " }",paramType ="query" ,required = true,dataType = "string",defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "api/v1/user/SetUserStatus", method = RequestMethod.POST)
    @ResponseBody
    public  String SetUserStatus(@RequestParam(value = "jsondata",required = true) String jsondata,
                                  HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("SetUserStatus");
            Map<String,String> new_map_String = new HashMap();
            for(Object key:maprequest.keySet()){
                new_map_String.put(key+"", String.valueOf(maprequest.get(key)==null? "":maprequest.get(key)));
                System.out.println(key+" : "+String.valueOf(maprequest.get(key)));
            }

            result=userConntrollerService.UserComm(new_map_String,jsondata,"小程序总部 启用/禁用员工 SetUserStatus");
        } catch (ApiSysException |ApiOpException |UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 模糊查询员工准备修改 SelLikeUser (线下)",
            notes="小程序总部 模糊查询员工准备修改 SelLikeUser （线下） " +
            "说明  ： " +
            " in:(phone:电话号码，name:姓名\n" +
                    "-- )\n" +
                    "-- out:(result：成功返回1，失败返回0，phone:电话号码，name:姓名\n" +
                    "-- )备注：给出的示例只是公用参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"SelLikeUser\",\n" +
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
    @RequestMapping(value = "api/v1/user/SelLikeUser", method = RequestMethod.POST)
    @ResponseBody
    public  String SelLikeUser(@RequestParam(value = "jsondata",required = true) String jsondata,
                               HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("SelLikeUser");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=this.userConntrollerService.SelectUserInfoComm(maprequest,jsondata,"小程序总部 模糊查询员工准备修改 SelLikeUser");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }

    @ApiOperation(value="小程序总部 查询员工信息SelUserInfo(线下)",
            notes="小程序总部 查询员工信息 SelUserInfo（线下） " +
                    "说明  ： " +
                    "in:(phone:电话号码\n" +
                    "-- )\n" +
                    "-- out:(result：成功返回1，失败返回0，phone:电话号码，name:姓名，virtualshopid：门店编号,password:密码备注：给出的示例只是公用参数 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsondata", value = "{\n" +
                    "\t\"sqltext\": \"SelUserInfo\",\n" +
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
    @RequestMapping(value = "api/v1/user/SelUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public  String SelUserInfo(@RequestParam(value = "jsondata",required = true) String jsondata,
                               HttpServletRequest request){
        String result="";

        try {
            //第三种方式
            Map<String,Object> maprequest = JSON.parseObject(jsondata,Map.class);
            System.out.println("SelUserInfo");
            for (Object obj : maprequest.keySet()){
                System.out.println("key为："+obj+"  值为："+maprequest.get(obj));
            }

            result=this.userConntrollerService.SelectUserInfoComm(maprequest,jsondata,"小程序总部 查询员工信息SelUserInfo");
        } catch (ApiSysException|ApiOpException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(true, ""+ GlobalEumn.PARAMETERS_ERROR.getCode(),
                            GlobalEumn.PARAMETERS_ERROR.getMesssage(), (ResultMsg) null));
        }
        return result;
    }


}
