package com.aaa.huahui.model;

public class Shop {
    int shopid;
    String province;
    String city;
    String district;
    String geo;
    String controller;
    String phoneOrWechat;
    String mianji;
    String mainProject;
    Integer rooms;
    String rent;
    Integer beds;
    String single;
    int brandid;

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

    public Shop(int shopid, String province, String city, String district, String geo, String controller, String phoneOrWechat, String mianji, String mainProject, Integer rooms, String rent, Integer beds, String single, int brandid) {
        this.shopid = shopid;
        this.province = province;
        this.city = city;
        this.district = district;
        this.geo = geo;
        this.controller = controller;
        this.phoneOrWechat = phoneOrWechat;
        this.mianji = mianji;
        this.mainProject = mainProject;
        this.rooms = rooms;
        this.rent = rent;
        this.beds = beds;
        this.single = single;
        this.brandid = brandid;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
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

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getPhoneOrWechat() {
        return phoneOrWechat;
    }

    public void setPhoneOrWechat(String phoneOrWechat) {
        this.phoneOrWechat = phoneOrWechat;
    }

    public String getMianji() {
        return mianji;
    }

    public void setMianji(String mianji) {
        this.mianji = mianji;
    }

    public String getMainProject() {
        return mainProject;
    }

    public void setMainProject(String mainProject) {
        this.mainProject = mainProject;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }


}
