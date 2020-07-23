package com.example.demob;

public class Student {
    private String stuid;
    private String stuname;
    private int stuage;
    private int stusex;
    private int stuimg;

    public Student(String stuid, String stuname, int stuage, int stusex, int stuimg) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stuage = stuage;
        this.stusex = stusex;
        this.stuimg = stuimg;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getStuage() {
        return stuage;
    }

    public void setStuage(int stuage) {
        this.stuage = stuage;
    }

    public int getStusex() {
        return stusex;
    }

    public void setStusex(int stusex) {
        this.stusex = stusex;
    }

    public int getStuimg() {
        return stuimg;
    }

    public void setStuimg(int stuimg) {
        this.stuimg = stuimg;
    }
}
