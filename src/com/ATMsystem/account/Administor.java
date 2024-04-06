package com.ATMsystem.account;
import java.util.*;
import java.lang.*;

import com.ATMsystem.interver.Wait;

public class Administor extends Account{
    public Administor(String card, String password){
        this.card = card;
        this.password.append(password);
    }
    public void print_all_users(HashSet<User> users){
        int i;
        Iterator<User> it = users.iterator();
        if (users.size() == 0) System.out.println("当前没有任何用户");
        while(it.hasNext() == true){
            System.out.printf("%s %s 存款%d元\n", it.next().name, it.next().age, it.next().money);
        }
        Wait.last();
    }
    public void print_single_user(HashSet<User> users){
        Scanner scan = new Scanner(System.in);
        String card;
        System.out.print("输入用户的卡号:");
        card = scan.next();
        int i, key = 0;
        Iterator<User> it = users.iterator();
       while(it.hasNext() == true){
            if (card.compareTo(it.next().card) == 0){
                System.out.printf("%s %s 存款%d元\n", it.next().name, it.next().age, it.next().money);
                key = 1;
                break;
            }
        }
        if (key == 0) System.out.println("当前系统无该用户");
        Wait.last();
    }
    public void freeze(HashSet<User> users){
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入您要冻结的卡号:");
        String card = scan.next();
        int i, key = 0;
        Iterator<User> it = users.iterator();
        while(it.hasNext() == true){
            if (card.compareTo(it.next().card) == 0){
                it.next().isfreeze = true;
                System.out.printf("卡号 %s 冻结成功\n", card);
                key = 1;
                Wait.jixu();
            }
        }
        if (key == 0) {
            System.out.println("没有找到该卡号，请检查您的输入是否有误");
            Wait.last();
        }
    }
    public static void unfreeze(HashSet<User> users){
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入您要解冻的卡号:");
        String card = scan.next();
        int i, key = 0;
        Iterator<User> it = users.iterator();
        while(it.hasNext() == true){
            if (card.compareTo(it.next().card) == 0){
                it.next().isfreeze = false;
                it.next().count = 0;
                System.out.printf("卡号 %s 解冻成功\n", card);
                key = 1;
                Wait.jixu();
            }
        }
        if (key == 0) {
            System.out.println("没有找到该卡号，请检查您的输入是否有误");
            Wait.last();
        }
    }
    public static void changepassword(Administor admin){
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入原始密码:");
        String password = scan.next();
        if (password.compareTo(admin.password.toString()) != 0){
            System.out.println("密码错误");
            Wait.last();
            return;
        }
        while(true) {
            System.out.print("请输入新密码:");
            password = scan.next();
            System.out.print("再次输入新密码:");
            String password1 = scan.next();
            if (password.compareTo(password1) != 0){
                System.out.println("两次密码输入不一致，请重新输入");
                Wait.jixu();
                continue;
            }
            admin.password = new StringBuffer(password);
            System.out.println("密码修改成功");
        }
    }
    public void cancelaccout(HashSet<User> users){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要注销的卡号:");
        String card = scan.next();
        int i;
        Iterator<User> it = users.iterator();
        while(it.hasNext() == true){
            if(card.compareTo(it.next().card) == 0){
                it.remove();
                System.out.println("注销成功");
                Wait.jixu();
                return;
            }
        }
        System.out.println("没有找到该卡号，请检查您的输入是否有误");
        Wait.last();
    }
    public void exit(){
        Wait.exit();
    }

}
