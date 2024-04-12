package com.ATMsystem.input;

import com.ATMsystem.account.User;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.interver.Wait;

import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Userinput {
    public static void userinput(HashSet<User> users, User user) throws IOException, Transfer_myself {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.printf("尊敬的 %s ,您好\n", user.name);
            System.out.println("1、查询余额");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、贷款");
            System.out.println("6、还款");
            System.out.println("7、查看交易记录");
            System.out.println("8、修改密码");
            System.out.println("9、冻结账号");
            System.out.println("10、注销账号");
            System.out.println("11、退出");
            System.out.print("您的选择:");
            int key = 0;
            while (true) {
                try {
                    key = scan.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("请输入整数");
                    scan.nextLine();
                }
            }
            System.out.println("-------------------------------------------------------------");
            if (key == 1) user.check(user);
            else if (key == 2) user.deposit(user);
            else if (key == 3) user.withdraw(user);
            else if (key == 4) user.transfer(users, user);
            else if (key == 5) user.loan(user);
            else if (key == 6) user.payback(user);
            else if (key == 7) System.out.println("未实现");
            else if (key == 8) user.changepassword(user);
            else if (key == 9) {
                user.freeze(null, user);
                break;
            }
            else if (key == 10) {
                if (user.cancelaccout(users, user) == 0) break;
            }
            else if (key == 11) user.exit();
            else{
                Wait.error();
            }
        }
    }
}
