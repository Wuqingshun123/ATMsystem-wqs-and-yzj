package com.ATMsystem.input;

import com.ATMsystem.account.Administor;
import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;
import com.data.Time;
import com.data.cancel.Cancel;
import com.data.record.Recordread;
import com.data.record.Recordwrite;

import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administorinput {
    public static void administorinput(HashSet<User> users) throws IOException {
            Scanner scan = new Scanner(System.in);
            while(true) {
                int key = 0;
                while (true) {
                    System.out.println("尊敬的ATM系统管理员，您好");
                    System.out.println("1、查询所有用户          6、查询单个用户交易记录");
                    System.out.println("2、查询单个用户          7、修改管理员密码");
                    System.out.println("3、冻结账号             8、注销客户账号");
                    System.out.println("4、账号解冻             9、退出登录");
                    System.out.println("5、查询交易记录表        10、重置系统");
                    System.out.print("您的选择:");
                    try {
                        key = scan.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("请输入整数");
                        scan.nextLine();
                        Wait.jixu();
                        continue;
                    }
                }
                System.out.println("-------------------------------------------------------------");
                Administor administor = new Administor("admin", Administor.password.toString());
                if (key == 1)
                    administor.print_all_users(users);

                else if (key == 2)
                    administor.print_single_user(users);

                else if (key == 3)
                    administor.freeze(users, null);

                else if (key == 4){
                    Administor.unfreeze(users);
                }
                else if (key == 5){
                    Recordread.read();
                    Wait.jixu();
                }
                else if (key == 6){
                    administor.checksingletrade(users);
                }
                else if (key == 7){
                    Administor.changepassword();
                }
                else if (key == 8){
                    administor.cancelaccout(users, null);
                }
                else if (key == 9){
                    administor.exit();
                    break;
                }
                else if(key == 10){
                    Cancel.cancel();
                    Recordwrite.write2(String.format("%s  系统重置\n", Time.gettime()));
                }
                else{
                    Wait.error();
                }
            }
        }
    }

