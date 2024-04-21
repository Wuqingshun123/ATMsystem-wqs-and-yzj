package com.ATMsystem.account;
import java.io.IOException;
import java.util.*;
import java.lang.*;

import com.ATMsystem.interver.Wait;
import com.ATMsystem.main.Main;
import com.data.Time;
import com.data.file_storage.AtmDao;
import com.data.record.Recordread;
import com.data.record.Recordwrite;

public class Administor extends Account{
    public static StringBuffer password;

    static {
        try {
            password = new StringBuffer(Recordread.read2());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Administor(String card, String password){
        this.card = card;
        this.password = new StringBuffer(password);
    }
    public void print_all_users(HashSet<User> users){ //
        int i;
        if (users.size() == 0) System.out.println("当前没有任何用户");
        for(User user : users){
            System.out.printf("<卡号：%s <姓名：%s <年龄：%s <信誉积分：%d分 <当前存款%.2f元 <当前欠款%.2f元\n", user.card , user.name, user.age, user.credit, user.money, user.loan);
        }
        Wait.last();
    }
    public void print_single_user(HashSet<User> users){
        Scanner scan = new Scanner(System.in);
        String card;
        System.out.print("输入用户的卡号:");
        card = scan.next();
        int i, key = 0;
        for(User user : users){
            if (card.compareTo(user.card) == 0){
                System.out.printf("<用户姓名：%s <用户年龄：%s <用户信誉积分：%d分 <当前存款：%.2f元 <当前欠款：%.2f元\n", user.name, user.age, user.credit, user.money, user.loan);
                key = 1;
                break;
            }
        }
        if (key == 0) System.out.println("当前系统无该用户");
        Wait.last();
    }
    public void freeze(HashSet<User> users, User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入您要冻结的卡号:");
        String card = scan.next();
        int i, key = 0;
        for(User u : users){
            if (card.compareTo(u.card) == 0){
                u.isfreeze = true;
                System.out.printf("卡号 %s 冻结成功\n", card);
                AtmDao.updateData(users);
                Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 冻结卡号\n\n", Time.gettime(), u.card, u.name));
                Main.update();
                key = 1;
                Wait.jixu();
                return;
            }
        }
        if (key == 0) {
            System.out.println("没有找到该卡号，请检查您的输入是否有误");
            Wait.last();
        }
    }
    public static void unfreeze(HashSet<User> users) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入您要解冻的卡号:");
        String card = scan.next();
        int i, key = 0;
        for(User user : users){
            if (card.compareTo(user.card) == 0){
                user.isfreeze = false;
                user.count = 0;
                System.out.printf("卡号 %s 解冻成功\n", card);
                key = 1;
                Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 解冻卡号\n\n", Time.gettime(), user.card, user.name));
                Main.update();
                Wait.jixu();
            }
        }
        if (key == 0) {
            System.out.println("没有找到该卡号，请检查您的输入是否有误");
            Wait.last();
        }

    }
    public static void changepassword() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入原始密码:");
        String password = scan.next();
        if (password.compareTo(Administor.password.toString()) != 0){
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
            Administor.password = new StringBuffer(password);
            System.out.println("密码修改成功");
            Recordwrite.write2(String.format("%s 修改密码为%s\n", Time.gettime(), password));
            Recordwrite.write3(password);
            Wait.jixu();
            break;
        }
    }
    public int cancelaccout(HashSet<User> users, User u) throws IOException {
        synchronized (Administor.class) {
            Scanner scan = new Scanner(System.in);
            String card;
            System.out.print("输入用户的卡号:");
            card = scan.next();
            int i;
            for (User user : users) {
                if (card.compareTo(user.card) == 0) {
                    user.cancelaccout(users, user);
                    return 1;
                }
            }
            System.out.println("当前系统无该用户");
            Wait.last();
            return -1;
        }
    }
    public void exit(){
        Wait.exit();
    }
    public void checksingletrade(HashSet<User> users) throws IOException {
        Scanner scan = new Scanner(System.in);
        String card;
        System.out.print("输入用户的卡号:");
        card = scan.next();
        int i, key = 0;
        for(User user : users){
            if (card.compareTo(user.card) == 0){
                User.checktrade(user);
                break;
            }
        }
        if (key == 0) System.out.println("当前系统无该用户");
    }

}
