package com.edu.util;

import java.io.Serializable;


public class JsonP implements Serializable{

    private static final Long serialVersionUID=1696316166136161L;

    private static final String status="status";
    private static final String detail="detail";
    private static String msg;
    private static String code;
    private Object data;

    public JsonP(){
    }
    public JsonP(Object data){
        this.data =data;
    }
    public JsonP(Object data,String msg,String code){
        this.data =data;
        this.msg=msg;
        this.code=code;
    }

    public static JsonP success(){
        return newInstance(null,detail+":"+msg,status+":1");
    }
    public static JsonP success(Object data){
        return newInstance(data,detail,status+":1");
    }

    public static JsonP error(){
        return newInstance(null,detail,status+":0");
    }
    public static JsonP error(String msg,String code){
        return newInstance(null,detail+":"+msg,status+":"+code);
    }
    public static JsonP error(String msg){
        return newInstance(null,detail+":"+msg,status+":0");
    }

    public static JsonP newInstance(){
        return new JsonP();
    }
    public static JsonP newInstance(Object data){
        return new JsonP(data);
    }
    public static JsonP newInstance(Object data,String msg,String code){
        return new JsonP(data,msg,code);
    }

}
