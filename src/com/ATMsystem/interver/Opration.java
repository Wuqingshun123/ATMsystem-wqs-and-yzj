package com.ATMsystem.interver;

import com.ATMsystem.account.User;
import com.ATMsystem.input.Login;
import com.ATMsystem.input.Regist;


import java.util.HashSet;
import java.util.Scanner;

public class Opration {
    public static void initialoption(HashSet<User> users){
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("1、登录");
            System.out.println("2、注册");
            System.out.println("3、忘记密码");
            System.out.println("4、退出系统");
            System.out.print("在这里输入您的选择:");
            int key = scan.nextInt();
            System.out.println("-------------------------------------------------------------");
            if (key == 1) {
//                Login.login(users);
            } else if (key == 2) {
                Regist.regist(users);//注册功能入口
            } else if (key == 3) {

            } else if (key == 4) {
                return;
            }
        }
    }
}
