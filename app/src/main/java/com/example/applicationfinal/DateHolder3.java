package com.example.applicationfinal;

public class DateHolder3 {
    private static DateHolder3 instance;
    private String data2;

    private  DateHolder3(){}
    public static DateHolder3 getInstance(){
        if(instance==null){
            instance=new DateHolder3();
        }
        return instance;
    }
    public String getData(){
        return data2;
    }
    public void setData(String data2) {
        this.data2 = data2;
    }


}
