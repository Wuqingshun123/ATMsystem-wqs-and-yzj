package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Backpassword {
    public static void backpassword(HashSet<User> users){
        if (users.isEmpty()){
            System.out.println("当前系统无用户，先注册");
            Wait.exit();
            return;
        }
        Scanner scan = new Scanner(System.in);
        String card;
        String name;
        String phone;
        System.out.print("输入卡号:");
        card = scan.next();


        for(User user : users){
            if (card.compareTo(user.card) == 0){
                System.out.print("输入姓名:");
                name = scan.next();
                if (name.compareTo(user.name) != 0){
                    System.out.println("姓名错误，找回失败。");
                    Wait.exit();
                    return;
                }
                System.out.print("输入电话号码:");
                phone = scan.next();
                if (phone.compareTo(user.phone) != 0){
                    System.out.println("电话号码错误，找回失败。");
                    Wait.exit();
                    return;
                }
                System.out.println("密码找回成功！");
                System.out.printf("您的密码为%s\n", user.password);
                Wait.exit();
                return;
            }
        }
        System.out.println("当前系统无该用户");
        Wait.last();
    }
}
