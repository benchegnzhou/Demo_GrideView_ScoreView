package com.example.demomore.eventbusevent;

import android.text.TextUtils;


import com.example.demomore.eventbusevent.eventbusanyevent.ZBCAnyEventType;
import com.google.gson.Gson;

/**
 * Created by benchengzhou on 2017/3/25 17:06 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： UserLoginByCodeEvent
 * 备    注：
 */
public class UserLoginByCodeEvent extends ZBCAnyEventType {
    @Override
    public UserLoginByCodeEvent parseResult() {
        if (this.code == FAIL) {
            return null;
        }

        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserLoginByCodeEvent userloginevent= new Gson().fromJson(this.result, UserLoginByCodeEvent.class);
        return userloginevent;
    }





}
