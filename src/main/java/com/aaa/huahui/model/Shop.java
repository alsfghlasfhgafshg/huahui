package com.aaa.huahui.model;

public class Shop {
    int shopid;
    String description;
    String province;
    String city;
    String district;
    String geo;
    int brandid;

    public Shop(String description, String province, String city, String district, String geo, int brandid) {
        this.description = description;
        this.province = province;
        this.city = city;
        this.district = district;
        this.geo = geo;
        this.brandid = brandid;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public Shop(int shopid, String description, String geo, int brandid) {
        this.shopid = shopid;
        this.description = description;
        this.geo = geo;
        this.brandid = brandid;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public Shop() {
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
