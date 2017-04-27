package com.example.demomore.eventbusevent;

import android.text.TextUtils;

import com.example.demomore.entity.UserLoginGetCodeEntity;
import com.example.demomore.eventbusevent.eventbusanyevent.ZBCAnyEventType;
import com.google.gson.Gson;

/**
 * Created by benchengzhou on 2017/3/25 17:06 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： UserLoginByCodeEvent
 * 备    注：
 */
public class UserLoginByCodeGetCodeEvent extends ZBCAnyEventType {


    public UserLoginByCodeGetCodeEvent() {
    }


    @Override
    public UserLoginGetCodeEntity parseResult() {
        if (this.code == FAIL) {
            return null;
        }

        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserLoginGetCodeEntity userLoginGetCode = new Gson().fromJson(this.result, UserLoginGetCodeEntity.class);

        return userLoginGetCode;
    }


}
