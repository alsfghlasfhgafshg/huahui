package com.aaa.huahui.vo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AnalysisVO {

    String type;


    //将总计排序到最后
    PriorityQueue<AnalysisItemVO> con = new PriorityQueue<AnalysisItemVO>(new Comparator<AnalysisItemVO>() {
        @Override
        public int compare(AnalysisItemVO o1, AnalysisItemVO o2) {
            if (o1.category2.equals("总计")) {
                return 1;
            } else if (o2.category2.equals("总计")) {
                return -1;
            } else {
                return 0;
            }
        }
    });


    public AnalysisVO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//
//    public List<AnalysisItemVO> getCon() {
//        return con;
//    }
//
//    public void setCon(List<AnalysisItemVO> con) {
//        this.con = con;
//    }

    public PriorityQueue<AnalysisItemVO> getCon() {
        return con;
    }

    public void setCon(PriorityQueue<AnalysisItemVO> con) {
        this.con = con;
    }
}
