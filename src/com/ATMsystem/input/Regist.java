package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.interver.Wait;
import javax.xml.stream.XMLInputFactory;
import com.ATMsystem.account.Administor;
import com.ATMsystem.account.Account;
import com.ATMsystem.main.Main;
import com.data.file_storage.AtmDao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ATMsystem.input.Userinput.userinput;
import static com.ATMsystem.interver.Wait.jixu;
import static com.ATMsystem.interver.Wait.last;

public class Login {
    private static AtmDao atmDao = new AtmDao();
    public static void login(HashSet<User> users) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        String card;
        String password;
        int mistakeTimes = 3;
        System.out.println("尊敬的用户你好，欢迎使用登录功能");
a1:     while (true) {
            System.out.print("请输入你的卡号:");
            card = scan.next();
            if (card.compareTo("admin") == 0){
                System.out.print("请输入你的密码:");
                password = scan.next();
                if (password.compareTo(Administor.password.toString()) == 0){
                    System.out.println("登录成功");
                    System.out.println("-------------------------------------------------------------");
                    Administorinput.administorinput(users);
                    break a1;
                }
                else{
                    System.out.println("密码错误");
                    Wait.jixu();
                }
            }
            User user = null;
            for (User u : users){
                if (u.card.compareTo(card) == 0){
                    user = u;
                    break;
                }
            }
            if (user != null) {
                while (true) {
                    if (user.isfreeze) {
                        System.out.println("该账号已被冻结，请联系管理员解冻");
                        Wait.exit();
                        return;
                    }
                    System.out.print("请输入您的银行卡密码:");
                    password = scan.next();

                    if (password.compareTo(user.password.toString()) == 0) {
                        //接入用户界面
                        try {
                            System.out.printf("%s登录成功\n", user.name);
                            System.out.println("-------------------------------------------------------------");
                            Userinput.userinput(users, user);
                            return;
                        } catch (IOException | Transfer_myself e) {
                            System.out.println(e.getMessage());
                            scan.nextLine();
                        }
                    } else {
                        System.out.println("您输入的密码有误，请重新输入");
                        user.count++;
                        Main.update();
                        if (user.count >= 3) {
                            user.isfreeze = true;
                            Main.update();
                            System.out.println("你的账号:" + user.card + "已被冻结，请联系管理员申请解冻");
                            System.out.print("输入1继续登录操作，否则终止登陆操作:");
                            int key;
                            while (true) {
                                try {
                                    key = scan.nextInt();
                                    System.out.println("-------------------------------------------------------------");
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("请输入整数");
                                    scan.nextLine();
                                }
                            }
                            if (key == 1)
                                break;
                            else {
                                return;
                            }
                        }
                        System.out.println("今日若在输入错误" + (3 - user.count) + "次，您的账户将被冻结");
                        int key;
                        System.out.print("是否继续输入，如果是请继续输入1，否则输入任意结果:");
                        while (true) {
                            try {
                                key = scan.nextInt();
                                System.out.println("-------------------------------------------------------------");
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("请输入整数");
                                scan.nextLine();
                            }
                        }
                        if (key == 1) {
                            continue;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                System.out.println("您输入的卡号有误，请核对后输入。若尚未注册账户，请注册账户后登录。");
                int key;
                System.out.print("是否继续登录，如果是请继续输入 1 ，否则输入任意值:");
                while (true) {
                    try {
                        key = scan.nextInt();
                        System.out.println("-------------------------------------------------------------");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("请输入整数");
                        scan.nextLine();
                    }
                }
                if (key == 1)
                    continue;
                else
                    return;
            }
        }
    }
}
