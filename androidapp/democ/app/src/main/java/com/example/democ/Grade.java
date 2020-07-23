package com.example.democ;

public class Grade {
    private String stuname;
    private int android;
    private int english;
    private int graDel;

    public Grade(String stuname, int android, int english, int graDel) {
        this.stuname = stuname;
        this.android = android;
        this.english = english;
        this.graDel = graDel;
    }

    public int getGraDel() {
        return graDel;
    }

    public void setGraDel(int graDel) {
        this.graDel = graDel;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getAndroid() {
        return android;
    }

    public void setAndroid(int android) {
        this.android = android;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }
}
