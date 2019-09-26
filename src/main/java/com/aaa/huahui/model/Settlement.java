package com.aaa.huahui.model;

import java.sql.Timestamp;

public class Settlement {
    int id;
    int shopid;
    Timestamp timestamp;
    String customername;
    int price;
    int staffid;
    int category2id;
    String brandname;
    int paymentmethod;
    String consultant;
    int reporterid;

    public Settlement() {
    }

    public Settlement(int id, int shopid, Timestamp timestamp,
                      String customername, int price, int staffid,
                      int category2id, String brandname, int paymentmethod,
                      String consultant, int reporterid) {
        this.id = id;

        this.shopid = shopid;
        this.timestamp = timestamp;
        this.customername = customername;
        this.price = price;
        this.staffid = staffid;
        this.category2id = category2id;
        this.brandname = brandname;
        this.paymentmethod = paymentmethod;
        this.consultant = consultant;
        this.reporterid = reporterid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public int getCategory2id() {
        return category2id;
    }

    public void setCategory2id(int category2id) {
        this.category2id = category2id;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(int paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public int getReporterid() {
        return reporterid;
    }

    public void setReporterid(int reporterid) {
        this.reporterid = reporterid;
    }
}
