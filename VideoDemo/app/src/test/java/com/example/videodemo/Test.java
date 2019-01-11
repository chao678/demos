package com.example.videodemo;


import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

    @org.junit.Test
    public void test() {
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
        Map map = new Gson().fromJson(str, Map.class);
        for (Object key : map.keySet()) {
            System.out.println("key="+key+",value="+map.get(key));
        }
        System.out.println();
        for (Object entry : map.entrySet()) {
            Map.Entry e = (Map.Entry) entry;
            System.out.println("key="+e.getKey()+",value="+e.getValue());
        }

        Set set = new HashSet();
        set.add("1");
        set.add("222");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
        for (Object s : set) {
            System.out.println(s.toString());
        }
    }
}
