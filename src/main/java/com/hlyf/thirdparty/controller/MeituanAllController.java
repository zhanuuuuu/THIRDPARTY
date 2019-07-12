package com.hlyf.thirdparty.controller;

import com.alibaba.fastjson.JSON;
import com.hlyf.thirdparty.domain.Image;
import com.hlyf.thirdparty.result.GlobalEumn;
import com.hlyf.thirdparty.result.GlobalInfo;
import com.hlyf.thirdparty.result.ResultMsg;
import com.hlyf.thirdparty.service.MtService;
import com.hlyf.thirdparty.service.UserService;
import com.hlyf.thirdparty.tool.FileBean;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2019-06-24.
 */
@RestController
@Slf4j
@RequestMapping("/meituan")
@Api(value = "API - MeituanAllController", description = "美团API 二次封装 ")
public class MeituanAllController {

    @Autowired
    private MtService mtService;

    @ApiOperation(value="美团API二次封装", notes="美团万能接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId",paramType ="query" ,required = true,dataType = "string",defaultValue = "4115"),
            @ApiImplicitParam(name = "appSecret", value = "appSecret",paramType ="query" ,required = false,dataType = "string",defaultValue = "f0b1b7d92d96485e704316604a24bd5a"),
            @ApiImplicitParam(name = "requestMethod", value = "请求方法（eg:GET或POST）",paramType ="query" ,required = true,dataType = "string",defaultValue = "GET"),
            @ApiImplicitParam(name = "meituanurl", value = "美团接口地址(eg:https://waimaiopen.meituan.com/api/v1/poi/getids)",paramType ="query" ,required = true,dataType = "string",defaultValue = "https://waimaiopen.meituan.com/api/v1/poi/getids")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})
    @RequestMapping(value = "/api/transmit", method = RequestMethod.POST)
    @ResponseBody
    public  String transmit(
            @RequestParam(value = "appId",required = true) String appId,
            @RequestParam(value = "appSecret",required = false) String appSecret,
            @RequestParam(value = "requestMethod",required = true) String requestMethod,
            @RequestParam(value = "meituanurl",required = true) String meituanurl,
            HttpServletRequest request){
        String result="";
        try {
            result=mtService.transmitS(appId, appSecret, requestMethod, meituanurl, request);
        } catch (ApiSysException e) {
            e.printStackTrace();
            return "";
        } catch (ApiOpException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }


    /**
     * APP方URL 推送门店状态变更
     * @param request
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/api/poi/state/update", method = RequestMethod.POST)
    @ResponseBody
    public  String poistateupdate(@RequestParam(value = "timestamp",required = true) long timestamp,
                                  @RequestParam(value = "app_id",required = true) String app_id,
                                  @RequestParam(value = "sig",required = true) String sig,
                                  HttpServletRequest request){
        String result="{\"data\":\"ok\"}";
        try {

            result=mtService.poistateupdateS(request,"b0b1c772b3228fdd83c7ae797e7fbb44",sig);
        } catch (ApiSysException|UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (ApiOpException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }


    /**
     * 所有推送都在这里接收
     * @param request
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/api/getpush/{urlType}", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public  String getpush(@PathVariable String urlType,
                            @RequestParam(value = "timestamp",required = false,defaultValue = "1") long timestamp,
                            @RequestParam(value = "app_id",required = false,defaultValue = "1") String app_id,
                            @RequestParam(value = "sig",required = false,defaultValue = "1") String sig,
                            HttpServletRequest request){
        String result="{\"data\":\"ok\"}";
        try {
            result=mtService.getpushS(request,"f0b1b7d92d96485e704316604a24bd5a",sig,urlType);
        } catch (ApiSysException e) {
            e.printStackTrace();
            return "";
        } catch (ApiOpException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }

    @ApiOperation(value="图片上传的接口", notes="格式：imagesdata=[{name:\"1001.jpg\",base64image:\"图片转base64字符串\"},...]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imagesdata", value = "JSON格式",paramType ="query" ,required = true,dataType = "string",
                    defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})

    @RequestMapping(value = "/api/images", method = {RequestMethod.POST})
    @ResponseBody
    public  String images(
                           @RequestParam(value = "imagesdata",required = true) String imagesdata,
                           HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        JSONArray array =null;
        try{
            array = JSONArray.fromObject(imagesdata);
        }catch (Exception e){
            //数据解析错误  直接返回
            e.printStackTrace();
            log.error(e.getMessage());
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                            GlobalEumn.IMAGESJSON_ERROR.getMesssage(), (ResultMsg) null));
        }

        List<Image> images =null;
        try {
            images = JSONArray.toList(array, new Image(), new JsonConfig());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                            GlobalEumn.IMAGESJSON_ERROR.getMesssage(), (ResultMsg) null));
        }
        String imageUrl=request.getSession().getServletContext().getRealPath("")
                .replace("Thirdparty","3Helper");
        File file = new File(request.getSession().getServletContext().getRealPath("")
                .replace("Thirdparty","3Helper"));
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            for(Image image :images){
                    try{
                        FileBean.generateImage(image.getBase64image(),imageUrl+image.getName());
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error(e.getMessage());
                        return com.alibaba.fastjson.JSONObject.toJSONString(
                                new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                                        GlobalEumn.IMAGESJSON_ERROR.getMesssage(), (ResultMsg) null));
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                            GlobalEumn.IMAGESJSON_ERROR.getMesssage(), (ResultMsg) null));

        }
        return com.alibaba.fastjson.JSONObject.toJSONString(
                new ResultMsg(true, ""+ GlobalEumn.SUCCESS.getCode(), GlobalEumn.SUCCESS.getMesssage(), (ResultMsg) null));
    }

    @ApiOperation(value="图片上传的接口form表单提交", notes="上传一个中文字符串 测试一下是否乱码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imagesdata", value = "上传一个中文字符串 测试一下是否乱码",paramType ="query" ,required = true,dataType = "string",
                    defaultValue = ""),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful — 请求已完成",reference="77777",responseContainer="8888888"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")})

    @RequestMapping(value = "/api/imagesform", method = {RequestMethod.POST})
    @ResponseBody
    public  String imagesform(
            @RequestParam(value = "imagesdata",required = true) String imagesdata,
            HttpServletRequest request){
        log.info("我是拿到的数据 {}" ,imagesdata);
        System.out.println("我是拿到的数据 :"+imagesdata);

        List<MultipartFile> files = null;
        try{
            files =((MultipartHttpServletRequest)request).getFiles("fileName");
        }catch (Exception e){
            log.info("解析出错了",e.getMessage());
            e.printStackTrace();
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                            GlobalEumn.IMAGESJSON_ERROR.getMesssage(),  ""));
        }

        List<String> urlImages=new ArrayList<>();
        if(files.isEmpty()){
            return com.alibaba.fastjson.JSONObject.toJSONString(
                    new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                            GlobalEumn.IMAGESJSON_ERROR.getMesssage(),  ""));
        }

        String imageUrl=request.getSession().getServletContext().getRealPath("")
                .replace("Thirdparty","3Helper");
        File file1 = new File(request.getSession().getServletContext().getRealPath("")
                .replace("Thirdparty","3Helper"));
        if(!file1.exists()){
            file1.mkdirs();
        }

        String urlTrue="";
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            if(!file.isEmpty()){
                //从命名
                String imageType=fileName.substring(fileName.indexOf("."),fileName.length());
                String fileNameTrue=System.currentTimeMillis()+imageType;
                urlTrue=imageUrl + "/" + fileNameTrue;
                System.out.println(urlTrue);
                File dest = new File(urlTrue);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                    urlImages.add(fileNameTrue);
                }catch (Exception e) {
                    e.printStackTrace();
                    return com.alibaba.fastjson.JSONObject.toJSONString(
                            new ResultMsg(false, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                                    GlobalEumn.IMAGESJSON_ERROR.getMesssage(),  ""));
                }
            }
        }

        return (urlImages==null || urlImages.size()==0)?
                com.alibaba.fastjson.JSONObject.toJSONString(
                new ResultMsg(true, ""+ GlobalEumn.IMAGESJSON_ERROR.getCode(),
                        GlobalEumn.IMAGESJSON_ERROR.getMesssage(), ""))
                : StringEscapeUtils.unescapeJavaScript(com.alibaba.fastjson.JSONObject.toJSONString(
                new ResultMsg(true, ""+ GlobalEumn.SUCCESS.getCode(),
                        GlobalEumn.SUCCESS.getMesssage(), com.alibaba.fastjson.JSONObject.toJSONString(urlImages))));
    }
}
