package com.example.demomore.entity;

/**
 * Created by benchengzhou on 2017/3/25.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public class UserLoginEntity {


    /**
     * code : 200
     * message : 成功
     * result : {"status":0,"userName":"用户名","headImage":"http:www.baidu.com","phoneNum":1261555552,"token":2545565655645,"gender":0,"nickName":"sajdhsu","userId":"1545454545454","huanxinUserName":"154545sad4a5sd4sa5d","huanxinUserpassword":"154545sad4a5sd4sa5d"}
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
         * status : 0
         * userName : 用户名
         * headImage : http:www.baidu.com
         * phoneNum : 1261555552
         * token : 2545565655645
         * gender : 0
         * nickName : sajdhsu
         * userId : 1545454545454
         * huanxinUserName : 154545sad4a5sd4sa5d
         * huanxinUserpassword : 154545sad4a5sd4sa5d
         */

        private int status;
        private String userName;
        private String headImage;
        private int phoneNum;
        private long token;
        private int gender;
        private String nickName;
        private String userId;
        private String huanxinUserName;
        private String huanxinUserpassword;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(int phoneNum) {
            this.phoneNum = phoneNum;
        }

        public long getToken() {
            return token;
        }

        public void setToken(long token) {
            this.token = token;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHuanxinUserName() {
            return huanxinUserName;
        }

        public void setHuanxinUserName(String huanxinUserName) {
            this.huanxinUserName = huanxinUserName;
        }

        public String getHuanxinUserpassword() {
            return huanxinUserpassword;
        }

        public void setHuanxinUserpassword(String huanxinUserpassword) {
            this.huanxinUserpassword = huanxinUserpassword;
        }
    }
}
