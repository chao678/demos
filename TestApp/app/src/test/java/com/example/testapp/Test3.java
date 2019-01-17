package com.example.testapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test3 {
    private int count = 0;

    @Test
    public void text() {
        Map map = new HashMap();
        List list = new ArrayList();
        list.add("231");
        list.add("sada");
        map.put("key", list);
        String str = JSON.toJSONString(map);
        System.out.println(str);
//        File file = new File("E://1");
//        listAll(file);
//        System.out.println(count);
        int[] array = {2,34,5,1,2,7,5};
        PaiXu.maopao(array);
        for (int i = 0; i < array.length; i++) {
            if (i < array.length -1) {
                System.out.print(array[i]+",");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.println();
        int a = 1;
        int b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a);
        System.out.println("b=" + b);

        String s1 = "abc";
        String s2 = "abcd";
        String s3 = "abcdfg";
        String s4 = "1bcdfg";
        String s5 = "cdfg";
        System.out.println( s1.compareTo(s2) ); // -1 (前面相等,s1长度小1)
        System.out.println( s1.compareTo(s3) ); // -3 (前面相等,s1长度小3)
        System.out.println( s1.compareTo(s4) ); // 48 ("a"的ASCII码是97,"1"的的ASCII码是49,所以返回48)
        System.out.println( s1.compareTo(s5) ); // -2 ("a"的ASCII码是97,"c"的ASCII码是99,所以返回-2)
        test2();
    }

    public void listAll(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        listAll(files[i]);
                    } else {
                        System.out.println(files[i].getName());
                        count++;
                    }
                }
            }
        }
    }

    public void test2() {
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(3);
        nums.add(5);
        nums.add(1);
        nums.add(0);
        System.out.println(nums);
        Collections.sort(nums);//给集合排序
        System.out.println(nums);

        List<User> list = new ArrayList<>();
        list.add(new User(null, 21));
        list.add(new User("李四", 25));
        list.add(new User("王五", 21));
        System.out.println(list);
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                int i = user.getAge() - t1.getAge();
                if (i == 0) {
                    if (user.getName() != null && t1.getName() != null) {
                        return user.getName().compareTo(t1.getName());
                    } else if (user.getName() == null) {
                        return 1;
                    } else if (t1.getName() == null) {
                        return -1;
                    }
                }
                return i;
            }
        });
        System.out.println(list);
        System.out.println("张".compareTo("王"));
    }

    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "name:" + this.name + " & age:" + this.age;
        }
    }

    @Test
    public void test3() {
        Map map = new HashMap();
        List list = new ArrayList();
        list.add("231");
        list.add("sada");
        map.put("key", list);
        JSON.toJSONString(map);
    }
}
