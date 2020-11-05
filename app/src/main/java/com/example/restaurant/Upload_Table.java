package com.example.restaurant;

import com.google.firebase.database.Exclude;

public class Upload_Table {
    private String mTotalTable;
    private String mKey;

    public Upload_Table() {

    }

    public Upload_Table(String mTotalTable) {
        this.mTotalTable = mTotalTable;
    }

    public String getmTotalTable() {
        return mTotalTable;
    }

    public void setmTotalTable(String mTotalTable) {
        this.mTotalTable = mTotalTable;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}


