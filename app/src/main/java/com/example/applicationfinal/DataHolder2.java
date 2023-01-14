package com.example.applicationfinal;

public class DataHolder2 {
        private static DataHolder2 instance;
        private String data;
        public static DataHolder2 getInstance(){
            if(instance==null){
                instance=new DataHolder2();
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


