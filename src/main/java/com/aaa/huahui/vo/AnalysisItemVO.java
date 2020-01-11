package com.aaa.huahui.vo;

import java.util.ArrayList;

public class AnalysisItemVO {
    String category2;
    double summoney;
    double sumcount;

    ArrayList<AnalysisItemDetail> details = new ArrayList<>();

    public ArrayList<AnalysisItemDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<AnalysisItemDetail> details) {
        this.details = details;
    }

    public AnalysisItemVO() {
    }

    public AnalysisItemVO(String category2, Double summoney, Double sumcount) {
        this.category2 = category2;
        this.summoney = summoney;
        this.sumcount = sumcount;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public Double getSummoney() {
        String sc = String.format("%.2f", summoney);//保留两位小数
        return Double.parseDouble(sc);
    }

    public void setSummoney(double summoney) {
        this.summoney = summoney;
    }

    public Double getSumcount() {
        String sc = String.format("%.2f", sumcount);
        return Double.parseDouble(sc);
    }

    public void setSumcount(double sumcount) {
        this.sumcount = sumcount;
    }
}
