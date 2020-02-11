package com.aaa.huahui.model;

public class Brand {
    int brandid;
    String description;
    String controller;
    String charger;
    String avatar;
    String province;
    String city;
    String district;
    String geo;

    public Brand(int brandid, String description, String avatar) {
        this.brandid = brandid;
        this.description = description;
        this.avatar = avatar;
    }

    public Brand(int brandid, String description, String controller, String avatar) {
        this.brandid = brandid;
        this.description = description;
        this.controller = controller;
        this.avatar = avatar;
    }

    public Brand(int brandid, String description, String controller, String charger, String avatar, String province, String city, String district, String geo) {
        this.brandid = brandid;
        this.description = description;
        this.controller = controller;
        this.charger = charger;
        this.avatar = avatar;
        this.province = province;
        this.city = city;
        this.district = district;
        this.geo = geo;
    }

    public Brand() {
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }
}
