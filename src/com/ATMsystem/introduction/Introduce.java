package com.ATMsystem.introduction;
import com.ATMsystem.interver.Wait;

import java.util.*;
import java.lang.*;
public class Introduce {
    public static void printintroduce() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("尊敬的用户，欢迎来到ATM模拟系统！");
        System.out.println("-------------------------------------------------------------");
        System.out.println("在使用ATM模拟系统之前，先请您阅读作者的前言。");
        Wait.jixu();
        System.out.println("1、本系统由 wqs 和 yzj 合作打造，搬运需经作者许可");
        System.out.println("2、本系统无初始用户，普通用户必须先注册");
        System.out.println("3、本系统有系统管理员，登录管理员的初始账号和密码均是admin");
        System.out.println("4、本ATM系统所有操作的最小单位是1元，不要出现比1元小的输入");
    }
}
