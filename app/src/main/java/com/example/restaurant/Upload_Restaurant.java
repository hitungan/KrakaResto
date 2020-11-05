package com.example.restaurant;

import com.google.firebase.database.Exclude;

public class Upload_Restaurant {
    private String mTableNum;
    private String mName;
    private int mQty;
    private double mPrice;
    private long mTime;
    private String key;

    public Upload_Restaurant() {

    }

    public Upload_Restaurant(String tableNum, String name, int qty, double price, long time) {
        mTableNum = tableNum;
        mName = name;
        mQty = qty;
        mPrice = price;
        mTime = time;
    }

    public String getmTableNum() {
        return mTableNum;
    }

    public void setmTableNum(String mTableNum) {
        this.mTableNum = mTableNum;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmQty() {
        return mQty;
    }

    public void setmQty(int mQty) {
        this.mQty = mQty;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
