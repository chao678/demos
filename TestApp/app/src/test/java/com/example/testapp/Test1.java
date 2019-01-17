package com.example.testapp;

import android.content.Intent;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Test1 {

    @Test
    public void test() {
        System.out.println("nihao");
        assertEquals(Integer.parseInt("1"),1);
//        for (int i = 0; i < 1000; i++) {
////            System.out.println(toChinese(i+""));
//            System.out.println(toChinese2(i+""));
//        }

        toChinese2("110000000");
    }

    private static String toChinese(String input) {
        String[] num = {"零","一","二","三","四","五","六","七","八","九","十"};

        String[] unit = {"","十","百","千","万","十","百","千","亿"};

        String[] result;
        String out = "";
        result = new String[input.length()];
        int length = result.length;
        for(int i = 0; i< length; i++) {
            result[i] = String.valueOf(input.charAt(i));
        }
        for(int i = 0; i< length; i++) {
            int back;
            if(!result[i].equals("0")) {
                back = length - i - 1;
                out += num[Integer.parseInt(result[i])];
                out += unit[back];
            } else {
                //最后一位不考虑
                if(i == (length - 1)) {
                    if(length > 4 && result[length - 1].equals("0") && result[length - 2].equals("0") && result[length - 3].equals("0") && result[length - 4].equals("0")){
                        out += unit[4];
                    }
                } else {
                    //九位数，千万，百万，十万，万位都为0，则不加“万”
                    if(length == 9 && result[1].equals("0") && result[2].equals("0") && result[3].equals("0") && result[4].equals("0")) {

                    } else {
                        //大于万位，连着的两个数不为0，万位等于0则加上“万”
                        if(length > 4 && !result[i+1].equals("0") && result[length -5].equals("0")){
                            out += unit[4];
                        }
                    }
                    //万位之后的零显示
                    if(i == length -4 && !result[i+1].equals("0")) {
                        out += num[0];
                    }
                }
            }
        }

        if (out.length() >= 2 && out.length() <= 3) {
            if ("一十".equals(out.substring(0,2))) {
                out = out.substring(1);
            }
        }
        return out;
    }

    private String toChinese2(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

        String result = "";

        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

//            if (i != n - 1 && num != 0) {
//                result += s1[num] + s2[n - 2 - i];
//            } else {
//                result += s1[num];
//            }
            System.out.println("  "+result);

        }

//        System.out.println("----------------");
//        System.out.println(result);

        return result;

    }
}
