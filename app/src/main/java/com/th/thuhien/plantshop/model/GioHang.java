package com.th.thuhien.plantshop.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class GioHang {
    public int idSP;
    public String tenSP;
    public long giaSP;
    public String hinhSP;
    public int soluongSP;

    public GioHang() {
    }

    public GioHang(int idSP, String tenSP, long giaSP, String hinhSP, int soluongSP) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hinhSP = hinhSP;
        this.soluongSP = soluongSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public long getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(long giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public int getSoluongSP() {
        return soluongSP;
    }

    public void setSoluongSP(int soluongSP) {
        this.soluongSP = soluongSP;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "idSP=" + idSP +
                ", tenSP='" + tenSP + '\'' +
                ", giaSP=" + giaSP +
                ", hinhSP='" + hinhSP + '\'' +
                ", soluongSP=" + soluongSP +
                '}';
    }


}
