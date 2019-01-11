package com.example.mydemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        A a = new A();
        a.a("1", "2", "3");

        int q = 2000;
        int w = 2000;
        Integer e = 2000;
        Integer r = 2000;
        System.out.println(q==w);
        System.out.println(e.equals(r));
        System.out.println(e==r);

        StringBuffer sb = new StringBuffer("15702497895");
        sb.replace(3,7,"****");
        System.out.println(sb.toString());

        int i = (int) 1.6d;
        System.out.println(i);
        double d1 = Double.parseDouble("1");
        double d2 = Double.parseDouble("1.6");
        System.out.println(d1+";"+d2+";"+(d1<d2));
        long l = Long.parseLong("1");
        System.out.println(l);

        long l1 = 1544674154123l;
        long l2 = 1000;
        System.out.println("3/2="+(l1/l2));
        System.out.println(new Date().getTime());

        String s12 = "41 35 35 41 0E 0E 01 01 02 17 00 29 33 43 30 30 32 32 30 30 30 42 35 31 33 37 33 31 33 33 33 38 33 34 33 32 30 30 30 30 30 30 30 30 30 14 13 01 02 10 01 39 03 50 23 41 41 46 46 23";
        System.out.println(s12.length());
        adf();

        String shuju = "35 41 41 35 0A 01 01 01 02 1B 00 0d 01 43 00 01 01 14 0F 0C 1D 04 07 23 01 e4 23 41 41 46 46 23";
        shuju = shuju.replace(" ", "");
        System.out.println(shuju);

        System.out.println(String.format("%02x", 10).toUpperCase());
        System.out.println(Integer.parseInt("0F", 16)+"");

        ArrayList<Map> list = new ArrayList();
        String data = "010203040506";
        int len = data.length();
        if (len >= 6 && len % 6 == 0) {
            for (int ii = 0; ii < len; ii = ii + 6) {
                HashMap<String, String> map = new HashMap<>();
                map.put("1", data.substring(ii, ii + 2));//类型
                map.put("2", data.substring(ii + 2, ii + 4));//回路
                map.put("3", data.substring(ii + 4, ii + 6));//地址
                list.add(map);
            }
        }
        System.out.println(new Gson().toJson(list));
    }
    class A {
        public void a(String... strings) {
            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }
            System.out.println("length="+strings.length);
        }
    }

    public void adf() {
        String s1 = String.valueOf(123456789);
        if (s1.length() >= 10) {
            s1 = s1.substring(0, 10);
        }
        System.out.println("s1="+s1);

//        int i = -0xf7;
        int i = 0xf7;
        String s = Integer.toBinaryString(i);
        System.out.println(i);
        System.out.println(s);
        System.out.println(i & 0xFF);
        System.out.println(Integer.toBinaryString(i & 0xFF));
        System.out.println(i & 0x0F);
        System.out.println(Integer.toBinaryString(i & 0x0F));
        System.out.println(Integer.toBinaryString(i >> 1));
        System.out.println(i >> 1);
        System.out.println(Integer.toBinaryString(i >>> 1));
        System.out.println(i >>> 1);
        System.out.println(" 11 22 33 44 ".trim());

        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Bundle bundle = msg.getData();
                        String num = bundle.getString("num");

                        break;
                }
            }
        };
        Message message = Message.obtain();
        message.what = 0;
        Bundle bundle = new Bundle();
        bundle.putString("num", "100");
        message.setData(bundle);
        myHandler.sendMessage(message);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //
            }
        }, 3000);//延迟3秒


        myHandler.postDelayed(r, 5000);
    }
}
