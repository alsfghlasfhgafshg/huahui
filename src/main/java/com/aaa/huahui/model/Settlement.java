package com.aaa.huahui.model;

import java.sql.Timestamp;
import java.util.List;


public class Settlement {
    int id;
    int shopid;
    Timestamp createtime;
    String customername;

    int peoplenum;
    String roomname;
    int consultantid;
    int paymentmethod;
    List<SettlementItem> settlementItems;

    int sumprice;

    public int getSumprice() {
        if (settlementItems == null) {
            return 0;
        } else {
            int sumprice = 0;
            for (SettlementItem settlementItem : settlementItems) {
                sumprice = sumprice + settlementItem.getPrice();
            }
            return sumprice;
        }
    }

    public List<SettlementItem> getSettlementItems() {
        return settlementItems;
    }

    public void setSettlementItems(List<SettlementItem> settlementItems) {
        this.settlementItems = settlementItems;
    }

    public Settlement() {
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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(int peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getConsultantid() {
        return consultantid;
    }

    public void setConsultantid(int consultantid) {
        this.consultantid = consultantid;
    }


    public Settlement(int shopid, Timestamp createtime, String customername, int peoplenum,
                      String roomname, int consultantid, int paymentmethod) {
        this.shopid = shopid;
        this.createtime = createtime;
        this.customername = customername;
        this.peoplenum = peoplenum;
        this.roomname = roomname;
        this.consultantid = consultantid;
        this.paymentmethod = paymentmethod;
    }

    public Settlement(int id, int shopid, Timestamp createtime, String customername,
                      int peoplenum, String roomname, int consultantid, int paymentmethod) {
        this.id = id;
        this.shopid = shopid;
        this.createtime = createtime;
        this.customername = customername;
        this.peoplenum = peoplenum;
        this.roomname = roomname;
        this.consultantid = consultantid;
        this.paymentmethod = paymentmethod;
    }

    public int getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(int paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
