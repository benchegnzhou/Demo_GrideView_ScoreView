package com.example.demomore.eventbusevent;

import android.text.TextUtils;

import com.example.demomore.entity.UserRegisterEntity;
import com.example.demomore.eventbusevent.eventbusanyevent.ZBCAnyEventType;
import com.google.gson.Gson;

/**
 * Created by zbc on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserRegisterEvent extends ZBCAnyEventType {
    public UserRegisterEvent() {
    }

    @Override
    public UserRegisterEntity parseResult() {
        if (this.code == FAIL) {
            return null;
        }

        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserRegisterEntity userregisterentity= new Gson().fromJson(this.result, UserRegisterEntity.class);

        return userregisterentity;
    }


}


