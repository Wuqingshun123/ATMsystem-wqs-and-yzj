package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.interver.Wait;
import javax.xml.stream.XMLInputFactory;
import com.ATMsystem.account.Administor;
import com.ATMsystem.account.Account;
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
    public static void login(HashSet<User> users) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String card;
        String password;
        int mistakeTimes = 3;
        System.out.println("尊敬的用户你好，欢迎使用登录功能");
        while (true) {
            System.out.print("请输入你的卡号:");
            card = scan.next();
            mistakeTimes = 3; //密码连续输入错误三次即冻结，如果输入错两次再重新登录刷新错误次数，可能要改
            User inputUser = new User(); // ************ 创建一个新的User对象
            inputUser.setaCard(card); // ************ 使用用户输入的卡号来初始化User对象
            User user = atmDao.getDate(users, inputUser); //******** 从内存中获取用户数据
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
                        } catch (IOException | Transfer_myself e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("您输入的密码有误，请重新输入");
                        mistakeTimes--;
                        if (mistakeTimes <= 0) {
                            user.isfreeze = true;
                            System.out.println("你的账号:" + user.card + "已被冻结，请联系管理员申请解冻");
                            System.out.println("输入 1 继续登录操作，否则终止登陆操作");
                            int key;
                            while (true) {
                                try {
                                    key = scan.nextInt();
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
                        System.out.println("若在输入错误 " + mistakeTimes + " 次，您的账户将被冻结");
                        int key;
                        System.out.print("是否继续输入，如果是请继续输入 1 ，否则输入任意结果");
                        while (true) {
                            try {
                                key = scan.nextInt();
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
                System.out.print("是否继续登录，如果是请继续输入 1 ，否则输入任意值。");
                while (true) {
                    try {
                        key = scan.nextInt();
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
