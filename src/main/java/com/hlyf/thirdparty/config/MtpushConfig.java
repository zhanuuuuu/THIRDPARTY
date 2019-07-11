package com.hlyf.thirdparty.config;

/**
 * 说明：
 *   在美团配置的时间 遵循的原则
 *   示例 1  接收美团 推送门店状态变更的url
 *        地址:http://148.70.87.251:8080/Thirdparty/meituan/api/getpush/storestateupdate(真实美团应该的配置)
 *       备注:http://ip:端口/Thirdparty/meituan/api/getpush/ 不变  后面拼接对应的地址码  storestateupdate
 */
public interface MtpushConfig {

    //接收美团 推送门店状态变更的url
    final static String STORESTATEUPDATE="storestateupdate";

    //接收美团 推送新增商品信息的url
    String GOODADD="goodadd";

    //接收美团 推送商品信息变更的url
    String GOODUPDATE="goodupdate";

    //接收美团 推送订单配送状态的url
    String ORDERSENDSTATE="ordersendstate";

    //接收美团 推送已支付订单（必接）的url
    String ORDERHASPAY="orderhaspay";

    //接收美团 推送已确认订单的url
    String ORDERSURE="ordersure";

    //接收美团 推送已完成订单的url
    String ORDERCOMPLETE="ordercomplete";

    //接收美团 推送订单结算信息的url
    String ORDERSETTLEMENTINFORMATION="ordersettlementinformation";

    //接收美团 推送催单消息（必接）的url
    String REMINDERORDER="reminderorder";

    //接收美团 隐私号降级通知推送（必接）url
    String PRIVACYPHONEREDUCE="privacyphonereduce";

    //接收美团 推送订单信息修改消息的url
    String ORDERMESSAGESUPDATE="ordermessagesupdate";

    //接收美团 推送拣货完成消息的url
    String PICKFINISH="pickfinish";

    //接收美团 推送客服赔付商家责任订单信息的url
    String KEFUPAYSTOREBLAMEORDER="kefupaystoreblameorder";

    //接收美团 推送用户或客服取消订单（必接）url
    String USERORKEFUCANCELORDER="userorkefucancelorder";

    //接收美团 推送全额退款信息（必接）url
    String ALLMONEYRETURN="allmoneyreturn";

    //接收美团 推送部分退款信息（必接）url
    String PARTMONEYRETURN="partmoneyreturn";
}
