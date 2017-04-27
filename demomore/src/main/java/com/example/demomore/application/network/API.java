package com.example.demomore.application.network;

import com.example.demomore.BuildConfig;
import com.example.demomore.application.MApplication;
import com.example.demomore.utils.DeviceMessageUtils;

import java.util.HashMap;

/**
 * Created by zbc on 2017/3/15.
 */

public class API {

    //服务主机
    public static final String SERVICE_HOST = BuildConfig.HOST_SERVICE;


    //用户密码登录登录
    public static final String USER_LOGIN_BYPASTWORD = SERVICE_HOST;

    //用户验证码登录
    public static final String USER_LOGIN_BYCODE = SERVICE_HOST ;

    //用户token登录
    public static final String USER_LOGIN_BYTOKEN = SERVICE_HOST ;


    //用户登录获取验证码
    public static final String GETCODE_FOR_LOGIN = SERVICE_HOST ;


    //用户注册获取验证码
    public static final String GETCODE_FOR_REGISTER = SERVICE_HOST;

    //用户注册校验验证码
    public static final String CHECKCODE_FOR_REGISTER = SERVICE_HOST;
    //用户修改密码
    public static final String PASSWORD_CHANGE = SERVICE_HOST;


    //用户身份验证获取验证码
    public static final String GETCODE_FOR_OTHERS = SERVICE_HOST ;


    //用户注册
    public static final String USER_REGISTER = SERVICE_HOST;


    /**
     * 获取用户注册获取验证码的url
     *
     * @return
     */
    public static String getGetcodeForRegisterUrl() {
        return GETCODE_FOR_REGISTER + "?service=user&function=getCodeForRegister";
    }

    /**
     * 获取用户注册验证码校验的url
     *
     * @return
     */
    public static String getCheckcodeForRegisterUrl() {
        return CHECKCODE_FOR_REGISTER + "?service=user&function=checkZCCode";
    }

    /**
     * 获取用户密码登录的url
     *
     * @return
     */
    public static String getUserLoginByPasswordUrl() {
        return SERVICE_HOST + "?service=user&function=phoneNumLogin";
    }

    /**
     * 获取用户验证码登录获取验证码的url
     *
     * @return
     */
    public static String getUserLoginByCodeUrl() {

        return SERVICE_HOST + "?service=user&function=checkLoginCode";
    }

    public static String getUserLoginByTokenUrl() {

         return SERVICE_HOST + "?service=user&function=tokenLogin";
    }

    /**
     * 获取用户验证码登录的url
     *
     * @return
     */
    public static String getGetCodeForLoginUrl() {
        return SERVICE_HOST + "?service=user&function=getCodeForLogin";
    }

    /**
     * 获取用户密码修改的url
     *
     * @return
     */
    public static String getChangePasswordUrl() {
        return SERVICE_HOST + "?service=user&function=updatePassWord";
    }
    /**
     * 获取用户已购买服务订单的url
     *
     * @return
     */
    public static String getUserBuyServiceUrl() {
        return "http://192.168.1.80:9090/house/myBuyServiceOrder.json";
    }
    /**
     * 获取用户已出售服务订单的url
     *
     * @return
     */
    public static String getUserSellServiceUrl() {
        return "http://192.168.1.80:9090/house/mySellServiceOrder.json";
    }
    /**
     * 获取用户帮助的订单的url
     *
     * @return
     */
    public static String getUserHelpUrl() {
        return "http://192.168.1.80:9090/house/SeekHelpOrderHelper.json";
    }
    /**
     * 获取用户求助的订单的url
     *
     * @return
     */
    public static String getUserNeedHelpUrl() {
        return "http://192.168.1.80:9090/house/SeekHelpOrderLaunch.json";
    }
    /**
     * 获取用户房屋列表的url
     *
     * @return
     */
    public static String getMyHouseListUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }

    /**
     * 用户添加房屋关注的url
     *
     * @return
     */
    public static String getMyHouseAttendUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }

    /**
     * 用户取消房屋关注的url
     *
     * @return
     */
    public static String getCancelMyHouseAttendUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户添加房屋绑定的url
     *
     * @return
     */
    public static String getAddMyHouseBindUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户取消房屋绑定的url
     *
     * @return
     */
    public static String getCancelMyHouseBindUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户获取同小区好友url
     *
     * @return
     */
    public static String getGetSameTownFriendsUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户获取活跃的好友url
     *
     * @return
     */
    public static String getGetActivFriendsUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户添加好友url
     *
     * @return
     */
    public static String getAddFriendsUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户添加好友url
     *
     * @return
     */
    public static String getDeleteFriendsUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户获取我的小区url
     *
     * @return
     */
    public static String getMyCommunityUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户添加小区url
     *
     * @return
     */
    public static String getAddMyCommunityUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }
    /**
     * 用户删除小区url
     *
     * @return
     */
    public static String getDeleteMyCommunityUrl() {
        return "http://192.168.1.80:9090/house/myHose.json";
    }


    /**
     * 获取社区便民信息的url
     *
     * @return
     */
    public static String getCommunityConvenienceUrl() {
        return "http://192.168.1.80:9090/house/convenienienceMessage.json";
    }

    /**
     * 拼接公共的请求参数
     * 所有的公共参数都放在这里
     *
     * @return
     */
    public static HashMap<String, String> getCommonParams() {
        HashMap<String, String> objectHashMap = new HashMap<>();
        objectHashMap.put("machineId", DeviceMessageUtils.getIMEI(MApplication.sAppContext));
        objectHashMap.put("machineName", DeviceMessageUtils.getMobileInfo(MApplication.sAppContext));
        objectHashMap.put("clientType", "1");
        return objectHashMap;
    }


}
