package com.aaa.huahui.utils;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;

public class PageInfoGen {
    public static HashMap gen(int pagenum,int sum) {
        HashMap<String,Integer> pageinfo = null;
        pageinfo.put("pagenum",pagenum);
        pageinfo.put("pages",sum);
        return pageinfo;
    }

    public static HashMap gen(PageInfo pageInfo) {
        HashMap<String,Integer> pageinfo = new HashMap<>();
        pageinfo.put("pagenum",pageInfo.getPageNum());
        pageinfo.put("pages",pageInfo.getPages());
        return pageinfo;
    }
}
