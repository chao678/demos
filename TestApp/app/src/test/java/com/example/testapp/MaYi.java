package com.example.testapp;

public class MaYi {
    private int weizhi;//位置
    private int fangxiang;//方向0左1右

    public MaYi(int weizhi, int fangxiang) {
        this.weizhi = weizhi;
        this.fangxiang = fangxiang;
    }

    public int getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(int weizhi) {
        this.weizhi = weizhi;
    }

    public int getFangxiang() {
        return fangxiang;
    }

    public void setFangxiang(int fangxiang) {
        this.fangxiang = fangxiang;
    }

    public int qianjin(int s) {
        if (fangxiang == 0) {
            weizhi = weizhi -s;
        } else {
            weizhi = weizhi + s;
        }
        return weizhi;
    }

    public void dioatou() {
        if (fangxiang == 0) {
            fangxiang = 1;
        } else {
            fangxiang = 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fangxiang;
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
        MaYi other = (MaYi) obj;
        if (fangxiang != other.fangxiang)
            return false;
        return true;
    }
}
