package com.example.firebasetuts.fragments;

public class Model {

    private String first ;
    private String last;
    private  String img;

    public Model(){

    }

    public Model(String first, String last, String img) {
        this.first = first;
        this.last = last;
        this.img = img;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
