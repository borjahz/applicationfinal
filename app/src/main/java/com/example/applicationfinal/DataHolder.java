package com.example.applicationfinal;

public class DataHolder {
private static DataHolder instance;
private String data;

private DataHolder(){}
    public static DataHolder getInstance(){
    if(instance==null){
        instance=new DataHolder();
    }
    return instance;
    }
    public String getData(){
    return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
