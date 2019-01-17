package com.example.testapp;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test2 {
    @Test
    public void test() {
//        MuGun muGun = new MuGun();
//        muGun.addMaYi(new MaYi(3, 0));
//        muGun.addMaYi(new MaYi(7, 1));
//        muGun.addMaYi(new MaYi(11, 0));
//        muGun.addMaYi(new MaYi(17, 0));
//        muGun.addMaYi(new MaYi(23, 1));
//        int time = muGun.start();
//        System.out.println(time);
        Set<Integer> timeSet = new HashSet<>();
        Set<MuGun> hashSet = new HashSet<>();
        while (true) {
            MuGun muGun = new MuGun();
            muGun.addMaYi(new MaYi(3, random()));
            muGun.addMaYi(new MaYi(7, random()));
            muGun.addMaYi(new MaYi(11, random()));
            muGun.addMaYi(new MaYi(17, random()));
            muGun.addMaYi(new MaYi(23, random()));
            hashSet.add(muGun);
            if (hashSet.size() == 32) {
                break;
            }
        }
        for (MuGun m: hashSet) {
            timeSet.add(m.start());
        }
        System.out.println(timeSet);
    }

    public static int random(){
        Random ran = new Random();
        int a = ran.nextInt(2);
        if(a==0){
            return 0;
        }else{
            return 1;
        }
    }
}
