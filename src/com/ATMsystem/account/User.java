package com.ATMsystem.account;

import java.util.Random;

public class User extends Account{
    public static String creatID(){
        Random random = new Random();
        char[] ram = new char[11];
        int i;
        for (i = 0; i < 11; i++){
            ram[i] = (char)(random.nextInt(10) + '0');
        }
        return new String(ram);
    }
    public void freeze(){
        System.out.println("这里只有伪代码");
        //对于用户有冻结自己账号的功能
    }
    public void exit(){
        System.out.println("这里只有伪代码");
        //退出用户登录的功能
    }

    public void changepassword(){
        System.out.println("这里只有伪代码");
        //修改用户密码的功能
    }
}
