package com.aaa.huahui.model;

public class WxOpenidChargerName {
    String openid;
    String charger;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public WxOpenidChargerName() {
    }

    public WxOpenidChargerName(String openid, String charger) {
        this.openid = openid;
        this.charger = charger;
    }
}
