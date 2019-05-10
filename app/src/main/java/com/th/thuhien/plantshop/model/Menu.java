package com.th.thuhien.plantshop.model;

public class Menu {
    private int maMenu;
    private String tenMenu;

    public Menu(){

    }

    public Menu(String tenMenu) {
        this.tenMenu = tenMenu;
    }

    public Menu(int maMenu, String tenMenu) {
        this.maMenu = maMenu;
        this.tenMenu = tenMenu;
    }

    public int getMaMenu() {
        return maMenu;
    }

    public void setMaMenu(int maMenu) {
        this.maMenu = maMenu;
    }

    public String getTenMenu() {
        return tenMenu;
    }

    public void setTenMenu(String tenMenu) {
        this.tenMenu = tenMenu;
    }
}