package com.ATMsystem.interver;

import java.util.Scanner;

public class Wait {
    public static void jixu(){
        Scanner scan = new Scanner(System.in);
        System.out.print("输入任意键继续:");
        scan.next();
        System.out.println("-------------------------------------------------------------");
    }
    public static void last(){
        Scanner scan = new Scanner(System.in);
        System.out.print("输入任意键返回上一步:");
        scan.next();
        System.out.println("-------------------------------------------------------------");
    }
    public static void exit(){
        Scanner scan = new Scanner(System.in);
        System.out.print("输入任意键退出:");
        scan.next();
        System.out.println("-------------------------------------------------------------");
    }
}
