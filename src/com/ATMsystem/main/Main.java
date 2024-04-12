package com.ATMsystem.main;

import com.ATMsystem.account.Administor;
import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.input.Regist;
import com.ATMsystem.interver.*;
import com.ATMsystem.introduction.Introduce;
import data.Time;
import data.record.Recordwrite;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static int count = 0;//用来记录有多少客户
    public static Administor admin = new Administor("admin", "admin");//创建管理员
    public static void main(String[] args) throws IOException, Transfer_myself {
        HashSet<User> users = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        Introduce.printintroduce();
        Wait.jixu();
        Opration.initialoption(users);//初始界面
    }
}
