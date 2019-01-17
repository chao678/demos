package com.example.testapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MuGun {
    private List<MaYi> list = new ArrayList<>();

    public void addMaYi(MaYi maYi) {
        list.add(maYi);
    }

    public void removeMaYi(int i) {
        list.remove(i);
    }

    public int getCount() {
        return list.size();
    }

    public int start() {
        int time = 0;
        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            time++;
            for (int i = 0; i < list.size(); i++) {
                list.get(i).qianjin(1);
                if (time == 1) {
                    System.out.println("蚂蚁"+i+"位置=" + list.get(i).getWeizhi());
                }
            }
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getWeizhi() <= 0 || list.get(i).getWeizhi() >= 27) {
                    c++;
                }
            }
            if (c == 5) {
                break;
            }

            if (list.get(0).getWeizhi() == list.get(1).getWeizhi()) {
                list.get(0).dioatou();
                list.get(1).dioatou();
            }
            if (list.get(1).getWeizhi() == list.get(2).getWeizhi()) {
                list.get(1).dioatou();
                list.get(2).dioatou();
            }
            if (list.get(2).getWeizhi() == list.get(3).getWeizhi()) {
                list.get(2).dioatou();
                list.get(3).dioatou();
            }
            if (list.get(3).getWeizhi() == list.get(4).getWeizhi()) {
                list.get(3).dioatou();
                list.get(4).dioatou();
            }

        }
        System.out.println("time=" + time);
        return time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((list == null) ? 0 : list.hashCode());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                result = prime * result + ((list.get(i) == null) ? 0 : list.get(i).hashCode());
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MuGun other = (MuGun) obj;
        if (list != other.list)
            return false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                if (other.list.get(i) != null)
                    return false;
            } else if (!list.get(i).equals(other.list.get(i)))
                return false;
        }
        return true;
    }
}
