package com.example.firebasetuts.fragments;

public class Model {

    private String Uid ;
    private String loginInfo;
    private int imageView;

    public Model(){

    }

    public Model(String uid, String loginInfo ) {
        Uid = uid;
        this.loginInfo = loginInfo;

    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }
}
