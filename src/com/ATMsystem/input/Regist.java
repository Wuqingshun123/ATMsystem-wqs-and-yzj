package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;

import java.util.*;
public class Regist {
    public static boolean islegal(String password){
        int i;
        if (password.length() != 6) return false;
        for (i = 0; i < 6; i++){
            if (Character.isDigit(password.charAt(i)) == false) return false;
        }
        return true;
    }//判断输入是否合法
    public static void regist(User[] users, int count){
        Scanner scan = new Scanner(System.in);
        String name;
        int age;
        String phone;
        String identity;
        String password;
        String password1;
        System.out.println("尊敬的用户，欢迎使用注册功能");
        System.out.println("如果要中途要退出，ATM模拟系统在最后有一个放弃选项");
        System.out.println("请在下方填写你的基本信息");
        System.out.print("您的姓名:");
        name = scan.next();
        System.out.print("您的年龄:");
        age = scan.nextInt();
        System.out.print("您的电话号码:");
        phone = scan.next();
        System.out.print("您的身份证号:");
        identity = scan.next();
        while(true) {
            while(true) {
                System.out.print("在这里输入您的登录密码:");
                password = scan.next();
                if (Regist.islegal(password) == false) {
                    System.out.println("密码必须为6位阿拉伯数字，请重新输入");
                }
                else break;
            }
            System.out.print("再次输入您的登录密码:");
            password1 = scan.next();
            if (password.compareTo(password1) != 0){
                System.out.println("两次密码输入不一致，提醒户主重新输入");
                continue;
            }
            break;
        }
        int key;
        System.out.print("是否继续此次操作，如果继续输入1，否则输入0:");
        key = scan.nextInt();
        if (key == 0) {
            System.out.println("终止操作成功");
            Wait.last();
            return;
        }
        User user = new User(name, age, phone, identity, password);
        System.out.printf("%s 恭喜您注册成功\n", user.name);
        System.out.printf("您的卡号是:%s\n", user.card);
        users[count++] = user;//添加用户
        Wait.last();
    }//注册功能的实现
}
