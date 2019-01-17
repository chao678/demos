package com.example.testapp;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PaiXu {

    public static void kuaisu(int[] array, int begin, int end) {
        if (begin < end) {
            int i = begin + 1;
            int j = end;
            int flag = array[begin];
            while (i < j) {
                while (i < j && array[j] >= flag) {
                    j--;
                }
                while (i < j && array[i] < flag) {
                    i++;
                }
                if (i < j) {
                    array[i - 1] = array[j];
                    array[j] = array[i];
                }
            }
            array[i] = flag;
            kuaisu(array, begin, i - 1);
            kuaisu(array, i + 1, end);
        }
    }

    public static void maopao(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if (array[j] > array[j+1]) {
                    int a = array[j];
                    array[j] = array[j+1];
                    array[j+1] = a;
                }
            }
        }
    }
}
