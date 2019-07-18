package com.hlyf.thirdparty.result;

/**
 * Created by Administrator on 2019-05-24.
 */
public enum  GlobalEumn implements GlobalInfo {

    SUCCESS(1001, "成功"),

    PARAMETERS_ERROR(1002, "上传的参数不合法,访问美团或本地数据失败"),
    IMAGESJSON_ERROR(1003, "上传数据解析出错"),
    JSON_ANALYZING_FAIL(1008, "服务端json返回时解析失败"),
    SUCCESS_ERROR(1009, "访问接口成功,但是执行保存我们的脚本失败了"),
    SUCCESS_ERRORTOW(1010, "访问美团接口失败了"),
    NO_STORE_SYSN(1011, "没有获取大需要同步的数据"),
    PROCE_ERROR(1012, "调用过程返回失败"),
    //小程序相关封装
    MINIPROGRAM_ERROR(2001,"访问小程序接口出错,请检查后台参数配置或者前台上传的参数是否正确"),
    MINIPROGRAM_DECRYPT(2002,"session_key过期,解密失败,或者上传的参数有误"),
    MINIPROGRAM_APPSECRET_ERROR(2003,"登陆失败,请先注册"),
    MINIPROGRAM_EMPTY(2004,"数据集为空"),
    MINIPROGRAM_GOODONLINE(2005,"不符合上架条件"),
    MINIPROGRAM_PRICE(2006,"不符合调价规则"),
    MINIPROGRAM_ADDGOOD(2007,"不符新增商品规则"),
    MINIPROGRAM_CATEGORY(2008,"不符新增商品分类规则"),
    SYSTEM_ERROR(9999,"参数上传有误,请检查参数");
    private int code;

    private String message;



    GlobalEumn(int code, String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMesssage() {
        return this.message;
    }
}
