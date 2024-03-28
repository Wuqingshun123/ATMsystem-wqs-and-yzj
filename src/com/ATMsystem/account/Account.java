package com.ATMsystem.account;
import java.util.*;
import java.lang.*;
public class Account {
    public String card;//账号
    public StringBuffer password = new StringBuffer();//密码
    public static void freeze(){
        System.out.println("这里不会执行");
    }//冻结功能
    public static void exit(){
        System.out.println("这里不会执行");
    }//退出功能

    public static void changepassword(){
        System.out.println("这里不会执行");
    }//修改密码功能

    public static void cancelaccout(){
        System.out.println("这里不会执行");
    }//注销功能
}
