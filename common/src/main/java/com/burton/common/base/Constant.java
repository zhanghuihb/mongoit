package com.burton.common.base;

/**
 * @author Tainy
 * @date 2018/6/11 15:04
 */
public class Constant {

    public static final int DEL_FLAG_NO = 1;
    public static final int DEL_FLAG_YES = 2;

    public static final int SPEND_COST = 1;// 已消费
    public static final int PRESUMING = 2;// 预消费

    public static final int INCOME = 1;// 收入
    public static final int EXPENDITURE = 2;//  支出

    public static String WX_AUTHORIZATION_PATH = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String WX_AUTHORIZATION_APPID = "wxfca30b8b12799b70";

    public static String WX_AUTHORIZATION_SECRET = "ab0c9a143c4c132da379c90956566e15";

    public static String WX_AUTHORIZE_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    public static String LTS_TRIGGER_TIME = "triggerTime";

    public static String LTS_CRON_EXPRESSION = "cronExpression";
}
