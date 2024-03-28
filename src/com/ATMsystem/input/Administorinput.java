package com.ATMsystem.input;

import com.ATMsystem.account.Administor;
import com.ATMsystem.account.User;

import java.util.Scanner;

public class Administorinput {
    public void administorinput(User[] users, int count, Administor admin){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("尊敬的ATM系统管理员，您好");
            System.out.println("1、查询所有用户");
            System.out.println("2、查询单个用户");
            System.out.println("3、冻结账号");
            System.out.println("4、账号解冻");
            System.out.println("5、修改管理员密码");
            System.out.println("6、注销客户账号");
            System.out.println("7、退出登录");
            int key = scan.nextInt();
            if (key == 1)
                Administor.print_all_users(users, count);

            else if (key == 2)
                Administor.print_single_user(users, count);

            else if (key == 3)
                Administor.freeze(users, count);

            else if (key == 4){
                Administor.unfreeze(users, count);
            }
            else if (key == 5){
                Administor.changepassword(admin);
            }
            else if (key == 6){
                Administor.cancelaccout(users, count);
            }
            else if (key == 7){
                Administor.exit();
            }
        }
    }
}
