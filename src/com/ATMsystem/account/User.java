package com.ATMsystem.account;

import java.util.HashSet;
import java.util.Random;

public class User extends Account{
    public String card;//账号
    public StringBuffer password = new StringBuffer();//密码
    boolean isfreeze = false;
    public String name;//名字
    public int age;//年龄
    public String phone;//电话号码
    public String identity;//身份证
    public double money;//存款
    public int count = 0;
    public User(String name, int age, String phone, String identity, String password){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.identity = identity;
        this.password.append(password);
        this.card = creatID();
        this.money = 100;
    }//构造方法
    public static String creatID(){//随机生成卡号
        Random random = new Random();
        char[] ram = new char[11];
        int i;
        for (i = 0; i < 11; i++){
            ram[i] = (char)(random.nextInt(10) + '0');
        }
        return new String(ram);
    }
    public void freeze(HashSet<User> users){
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

    @Override
    public void cancelaccout(HashSet<User> users) {

    }
}
