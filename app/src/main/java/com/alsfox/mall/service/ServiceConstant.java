package com.alsfox.mall.service;

public class ServiceConstant {
    /**
     * 微信 appid
     * 请同时修改 androidmanifest.xml里面，.PayActivityd里的属性
     * <data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
     */
    public static final String WEIXIN_APP_ID = "wx6d1416836d5d1721";

    /**
     * 微信支付回调广播标识
     */
    public static final String WEIXIN_PAY_BACK_TYPE = "WEIXIN_PAY_BACK_TYPE";

    /**
     * 支付宝
     */
    //商户PID
    public static final String PARTNER = "2088421964410881";
    //商户收款账号
    public static final String SELLER = "1249466903@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANIHKT3tylBdRT2Bzg0Knj99bp3Z6TutM0yQv3wzZKWtfZBth7UQs8rZR/NK+H6AfEYDNMyAaRwCCRIG44bPXenXj55His4QPt8oufyh57scodfQHJMA/AqYkV2A067D6Y//kWsUlFEfNOYhoYbJmNjjx7yu5EjmCZxsbYPcffPFAgMBAAECgYEAiMzPSR4ce/H0ipaE3qu+3X3W0gxAxXUQB9v0TXKwKNXzBmLBVOeXJBYYrlhzuPn5eCTYfCdbzzloLQy9oG5rLRld2zWmizRL2CNslom1hVVzwZ5OwakixWs+NPODW3e2OzQoeiacLhPlGue7Lrzkt0TTNps8QJUAOeNVmNoWi+ECQQD3aej0CPLIic2sKGhqaoxgdARBPIs/+/+Mqn9f+SAHFVxhM2bgIyeI+yKjSiFQtTZ3H1CuojKXC0AdK4MIunLNAkEA2VEbLboLbp9ZnNQ1GzZNVSjZLVLHoIqCAXaKQQVDacCXa7SPMCbwDL79yWauJrCMwnAo/HsVx4BWWeA3/aU02QJBAJe7XCJNWpeh64CpR1/7+BsvC2z2tG+JHDAoX/V70yiE8SKrY2wSIyze119NFyFoAP8NNW3kCyKWw7sf9DxmHAECQGYTGaAF1h7UzGPErVklzXclo7UNP+UhieUkB9Z4oWyH4NffTK6Ca5ihEVXLXBuiK8ucoTPOoLE/j8/RaJtEQSkCQHacNPLGFh7Iwm9KDmRLPywYXWVB3RdN9GDSCzsJmh1bB9ovKx4yF6lDjv6TWSbny3Xo/rghlYBjTocoMG9hIzk=";


}
