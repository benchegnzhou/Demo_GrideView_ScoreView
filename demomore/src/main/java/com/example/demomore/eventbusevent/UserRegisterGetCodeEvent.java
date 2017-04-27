package com.example.demomore.eventbusevent;

import android.text.TextUtils;

import com.example.demomore.entity.UserLoginGetCodeEntity;
import com.example.demomore.eventbusevent.eventbusanyevent.ZBCAnyEventType;
import com.google.gson.Gson;

/**
 * Created by zbc on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserRegisterGetCodeEvent extends ZBCAnyEventType {
    public UserRegisterGetCodeEvent() {
    }

    @Override
    public UserLoginGetCodeEntity parseResult() {
        if (this.code == FAIL) {
            return null;
        }

        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserLoginGetCodeEntity userlogingetcodeentity= new Gson().fromJson(this.result, UserLoginGetCodeEntity.class);

        return userlogingetcodeentity;
    }


}


