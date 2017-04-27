package com.example.demomore.entity;

/**
 * Created by benchengzhou on 2017/3/25.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public class UserRegisterGetCodeEntity {


    /**
     * code : 200
     * message : 成功
     * result : {"phoneNumStatus":0}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * phoneNumStatus : 0
         */

        private int phoneNumStatus;

        public int getPhoneNumStatus() {
            return phoneNumStatus;
        }

        public void setPhoneNumStatus(int phoneNumStatus) {
            this.phoneNumStatus = phoneNumStatus;
        }
    }
}
