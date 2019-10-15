package com.aaa.huahui.model;

public class Room {

    int id;
    int shopid;
    String roomname;

    public Room() {
    }

    public Room(int shopid, String roomname) {
        this.shopid = shopid;
        this.roomname = roomname;
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

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Room(int id, int shopid, String roomname) {
        this.id = id;
        this.shopid = shopid;
        this.roomname = roomname;
    }
}
