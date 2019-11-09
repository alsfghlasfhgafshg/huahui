package com.aaa.huahui;


import com.alibaba.fastjson.JSONArray;


import org.junit.Test;

import java.util.*;

public class NoSpringBootTest {

    @Test
    public void contextLoads() {


        PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
//                return o1 - o2;
                if (o1 == 3) {
                    return 1;
                } else if (o2 == 3) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        queue1.add(2);
        queue1.add(1);
        queue1.add(3);
        queue1.add(567);
        queue1.add(1);
        queue1.add(2);


        JSONArray j = new JSONArray();
        j.addAll(queue1);
        System.out.println(j.toJSONString());


    }
}
