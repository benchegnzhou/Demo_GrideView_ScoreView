package com.example.demomore.application.network.doparams;

import com.example.demomore.application.network.API;
import com.example.demomore.application.network.net.AsyncPostUtils;
import com.example.demomore.eventbusevent.UserLoginByCodeEvent;
import com.example.demomore.eventbusevent.UserRegisterGetCodeEvent;

import java.util.HashMap; 


/**
 * Created by zbc on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class ZBCOkHttpClient {
    //注册获取验证码
    public static void doUserLogin(String phoneNum) {
        String url = API.getGetcodeForRegisterUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        AsyncPostUtils.doAsyncPostKeyValue(url, params, new UserLoginByCodeEvent());
    }

    public static void doGetRegistCode(String phoneNum) {
        String url = API.getGetcodeForRegisterUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        AsyncPostUtils.doAsyncPostKeyValue(url, params, new UserRegisterGetCodeEvent());
    }



    /**
     * 我的小区
     * @param userId  用户id
     * @param token 令牌
     */
    public static void doGetMyCommunitys(String userId, String token) {
        String url = API.getMyCommunityUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        AsyncPostUtils.doAsyncPostKeyValue(url, params, new UserLoginByCodeEvent());
    }
    /**
     * 添加小区
     * @param userId  用户id
     * @param token 令牌
     * @param communityId 小区唯一id
     */
    public static void doAddMyCommunitys(String userId, String token,String communityId) {
        String url = API.getAddMyCommunityUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("communityId", communityId);
        AsyncPostUtils.doAsyncPostKeyValue(url, params, new UserRegisterGetCodeEvent());
    }
    /**
     * 添加小区
     * @param userId  用户id
     * @param token 令牌
     * @param communityId 小区唯一id
     */
    public static void doDeleteMyCommunitys(String userId, String token,String communityId) {
        String url = API.getDeleteMyCommunityUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("communityId", communityId);
        AsyncPostUtils.doAsyncPostKeyValue(url, params, new UserRegisterGetCodeEvent());
    }



}
