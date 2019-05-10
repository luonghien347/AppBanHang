package com.th.thuhien.plantshop.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int maSp;
    private String tenSp;
    private String hinhAnh;
    private String thongTin;
    private int giaSp;
    private int maMenu;

    public SanPham() {
    }

    public SanPham(int maSp, String tenSp, String hinhAnh, String thongTin, int giaSp, int maMenu) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.hinhAnh = hinhAnh;
        this.thongTin = thongTin;
        this.giaSp = giaSp;
        this.maMenu = maMenu;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public int getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(int giaSp) {
        this.giaSp = giaSp;
    }

    public int getMaMenu() {
        return maMenu;
    }

    public void setMaMenu(int maMenu) {
        this.maMenu = maMenu;
    }
}