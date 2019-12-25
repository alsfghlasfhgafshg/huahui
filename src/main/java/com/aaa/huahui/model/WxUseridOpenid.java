package com.aaa.huahui.model;

import java.io.Serializable;

public class WxUseridOpenid implements Serializable {
    int userid;
    String openid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public WxUseridOpenid() {
    }

    public WxUseridOpenid(int userid, String openid) {
        this.userid = userid;
        this.openid = openid;
    }
}
