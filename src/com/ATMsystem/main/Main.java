package com.ATMsystem.main;

import com.ATMsystem.account.Administor;
import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.input.Regist;
import com.ATMsystem.interver.*;
import com.ATMsystem.introduction.Introduce;
import com.data.file_storage.AtmDao;
import com.data.file_storage.FileLoadAndCommit;
import com.data.Time;
import com.data.record.Recordwrite;

import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
    public static int count = 0;//用来记录有多少客户
    public static Administor admin = new Administor("admin", "admin");//创建管理员
    public static HashSet<User> users = new HashSet<>();
    public static void main(String[] args) throws IOException, Transfer_myself {
        FileLoadAndCommit a = new FileLoadAndCommit(null);
        users = a.loadFile();
        Scanner scan = new Scanner(System.in);
        Introduce.printintroduce();
        Wait.jixu();
        Opration.initialoption(users);//初始界面
    }
    public static void update() throws IOException {

        AtmDao.updateDate(users);

    }
}
