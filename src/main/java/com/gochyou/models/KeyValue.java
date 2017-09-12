package com.gochyou.models;

public class KeyValue {

    private String key;
    private String val;

    public KeyValue(String key, String val){
        this.key = key;
        this.val = val;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
