package com.example.videodemo;

import android.util.Log;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);

        String s = "key=69898889acf35b269fd517d9a83617cf&time=1542269556";
//        String[] ss = s.split("&");
//        for (int i = 0; i < ss.length; i++) {
//            System.out.println(ss[i]);
//            System.out.println(ss[i].split("=")[1]);
//        }
//        System.out.println(s.indexOf("="));
//        System.out.println(s.substring(4, s.indexOf("&")));

//        SymmetricEncoder se=new SymmetricEncoder();
//        String hh = se.AESDncode("12f862d21d3ceafb", "Hec8ieTemGEq0pGjeVGZXTTbEpfBKei7s8FScIwz3ZYN33eOLIbseXFqJ5/DykLPd+VtgVREyuLSFMBQYvfr4g==");
//        System.out.println(hh);
//        try {
//            System.out.println(new String(hh.getBytes("UTF-8"), "gb2312"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String s1 = "Hec8ieTemGEq0pGjeVGZXTTbEpfBKei7s8FScIwz3ZYN33eOLIbseXFqJ5/DykLPd+VtgVREyuLSFMBQYvfr4g==";
//        String key = "12f862d21d3ceafb";
//
//        String decrypt = AES.getInstance().decrypt(s1);
//        System.out.println(decrypt);

        long l = Long.parseLong("1542101043000");
        Date d1 = new Date(l);
        long loginTime = System.currentTimeMillis();
        Date d2 = new Date(1542619443000l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("d1:"+sdf.format(d1));
        System.out.println("d2:"+sdf.format(d2));
        System.out.println("l:"+l);

        String ss = "1234";
        int i = ss.indexOf("=");
        System.out.println(i+"");
        System.out.println(ss.substring(i + 1));

        //编码：
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] ciphertext = "1234".getBytes();
        String AES_encode=encoder.encodeToString(ciphertext);
        //解码：
        String ciphertext_base64 = "MTIzNA==";
        Base64.Decoder decoder = Base64.getDecoder();
        byte [] byte_content= decoder.decode(ciphertext_base64);

        System.out.println(AES_encode);
        System.out.println(new String(byte_content));

    }
}