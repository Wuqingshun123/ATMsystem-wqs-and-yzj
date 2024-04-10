package com.ATMsystem.input;

import com.ATMsystem.account.Administor;
import com.ATMsystem.account.User;

import java.util.HashSet;
import java.util.Scanner;

public class Administorinput {
    public void administorinput(HashSet<User> users, Administor admin){
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
                System.out.print("您的选择:");
                System.out.print("您的选择:");
                int key = scan.nextInt();
                Administor administor = new Administor("admin", "admin");
                if (key == 1)
                    administor.print_all_users(users);

                else if (key == 2)
                    administor.print_single_user(users);

                else if (key == 3)
                    administor.freeze(users);

                else if (key == 4){
                    Administor.unfreeze(users);
                }
                else if (key == 5){
                    Administor.changepassword(admin);
                }
                else if (key == 6){
                    administor.cancelaccout(users);
                }
                else if (key == 7){
                    administor.exit();
                    break;
                }
            }
        }
    }

