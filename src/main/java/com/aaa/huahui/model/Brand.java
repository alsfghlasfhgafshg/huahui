package com.aaa.huahui.model;

public class Brand {
    int brandid;
    String description;
    String avatar;

    public Brand(int brandid, String description, String avatar) {
        this.brandid = brandid;
        this.description = description;
        this.avatar = avatar;
    }

    public Brand() {
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
}
