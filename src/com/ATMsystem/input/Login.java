package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;

import javax.xml.stream.XMLInputFactory;
import java.util.Scanner;

import static com.ATMsystem.interver.Wait.jixu;
import static com.ATMsystem.interver.Wait.last;

public class Login {
    public static void login(User[] users, int count){
        Scanner scan = new Scanner(System.in);
        String identity;
        String password;
        String password1 = null;
        int mistakeTimes = 3;
        System.out.println("尊敬的用户你好，欢迎使用登录功能");
        while (true) {
            System.out.println("请输入你的卡号");
            identity = scan.next();
            for (int i = count; i >0; i--) {
                if (users[i].card.compareTo(identity) != 0) {
                    System.out.println("您输入的卡号有误，请核对后输入");
                    System.out.println("如果您还未注册，请先注册后登录");
                    int key;
                    System.out.print("是否继续此次操作，如果继续输入1，否则输入0:");
                    key = scan.nextInt();
                    if (key == 0) {
                        System.out.println("终止操作成功");
                        return;
                    }
                }
                else {
                    System.out.println("请输入您的银行卡密码");
                    password = scan.next();
                    for(int j = count;j>0;j--){
                        if(password.compareTo(users[j].password.toString()) != 0){
                            mistakeTimes = 3;
                            System.out.println("恭喜您登陆成功");
                            jixu();
                        }
                        else{
                            if(mistakeTimes<=0){
                                System.out.println("密码输入错误三次，账户已被冻结");
                                System.out.println("请联系管理员解冻账户");
                                //冻结功能
                                last();
                            }
                            mistakeTimes--;
                            System.out.println("密码输入错误，请重新输入");
                            System.out.println("若在输入错误"+mistakeTimes+"次，您的账户将被冻结");

                        }

                    }
                }
            }

        }
    }
}

