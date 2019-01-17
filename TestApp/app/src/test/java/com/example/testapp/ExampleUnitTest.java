package com.example.testapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
//        tuzi();
//        main1();
        int[] array = {4,3,2,5,9,6};

        PaiXu.kuaisu(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println(this.getClass().getSimpleName());

        System.out.println("============================");
        A a = new A() {
            @Override
            public void b() {

            }
        };
        a.c();
    }

    private void tuzi() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < 12; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                int a = list.get(j);
                list.set(j, a + 1);
                if (list.get(j) >= 3) {
                    list.add(1);
                }
            }
            System.out.println("第" + (i+2) + "个月数量=" + list.size() * 2);
        }
    }


    public  void main1() {
        Scanner in = new Scanner(System.in);

//        System.out.println("你想知道前几个月的兔子的数量");
//        int month = in.nextInt();
        int month = 12;

        int[] mon = new int[month];
        if(month < 3){
            System.out.println("第" + month + "个月有 1 对兔子，共 2 只");
        }
        else
            for(int i = 2; i < month; i++){
                mon[0] = mon[1] = 1;
                mon[i] = mon[i - 1] + mon[i - 2];
                System.out.printf("第 %d 个月有 %d 对兔子，共 %d 只兔子\n", i + 1, mon[i], 2 * mon[i]);
            }
    }

}