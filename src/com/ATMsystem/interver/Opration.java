package com.ATMsystem.interver;

import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.input.Login;
import com.ATMsystem.input.Regist;
import com.ATMsystem.input.Userinput;


import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Opration {
    public static void initialoption(HashSet<User> users) throws IOException, Transfer_myself {
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("1、登录");
            System.out.println("2、注册");
            System.out.println("3、忘记密码");
            System.out.println("4、退出系统");
            System.out.print("在这里输入您的选择:");
            int key = 0;
            try {
                key = scan.nextInt();
            }catch (InputMismatchException e){
                System.out.println("请输入整数");
                Wait.jixu();
                continue;
            }
            System.out.println("-------------------------------------------------------------");
            if (key == 1) {
                Login.login(users);//登录功能入口
            } else if (key == 2) {
                Regist.regist(users);//注册功能入口
            } else if (key == 3) {

            } else if (key == 4) {
                return;
            }
            else{
                Wait.error();
            }
        }
    }
}
