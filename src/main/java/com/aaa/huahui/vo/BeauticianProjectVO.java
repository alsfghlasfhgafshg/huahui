package com.aaa.huahui.vo;

import java.util.List;

public class BeauticianProjectVO {
    String name;
    List<BeauticianProjectItemVO> con;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BeauticianProjectItemVO> getCon() {
        return con;
    }

    public void setCon(List<BeauticianProjectItemVO> con) {
        this.con = con;
    }

    public BeauticianProjectVO() {
    }

    public BeauticianProjectVO(String name, List<BeauticianProjectItemVO> con) {
        this.name = name;
        this.con = con;
    }
}
