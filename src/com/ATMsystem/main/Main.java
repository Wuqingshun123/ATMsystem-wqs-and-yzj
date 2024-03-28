package com.ATMsystem.main;

import com.ATMsystem.interver.*;
import com.ATMsystem.introduction.Introduce;

import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Introduce.printintroduce();
        Wait.jixu();
        System.out.println("1、登录");
        System.out.println("2、注册");
        System.out.println("3、忘记密码");
        int key = scan.nextInt();
    }
}
