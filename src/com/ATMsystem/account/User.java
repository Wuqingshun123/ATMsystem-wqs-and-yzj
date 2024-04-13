package com.ATMsystem.account;

import com.ATMsystem.exception.Less_than_fen;
import com.ATMsystem.exception.Less_than_zero;
import com.ATMsystem.exception.Transfer_myself;
import com.ATMsystem.input.Regist;
import com.ATMsystem.interver.Wait;
import com.ATMsystem.main.Main;
import com.data.Time;
import data.currency.Credit;
import com.data.record.Recordwrite;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.*;

public class User extends Account{
    public String card;//账号
    public StringBuffer password = new StringBuffer();//密码
    public boolean isfreeze = false;
    public String name;//名字
    public int age;//年龄
    public String phone;//电话号码
    public String identity;//身份证
    public double money = 100;//存款
    public int count = 0;
    public int credit = 60;
    public int transfers = 0;
    public double loan = 0;
    public boolean ispayback = false;
    public double daypayback = 0;
    public User(String name, int age, String phone, String identity, String password){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.identity = identity;
        this.password.append(password);
        this.card = creatID();
    }//构造方法

    //添加无参构造，FileLoadAndCommit类使用
    public User() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return card.equals(user.card); // ***** 比较两个User对象的卡号
    }


    public static String creatID(){//随机生成卡号
        Random random = new Random();
        char[] ram = new char[11];
        int i;
        for (i = 0; i < 11; i++){
            ram[i] = (char)(random.nextInt(10) + '0');
        }
        return new String(ram);
    }
    public void check(User user){
        double money = user.money;
        System.out.printf("您的当前可用余额为%.2f元\n", money);
        user.credit += 4;
        Wait.exit();
    }
    public void deposit(User user) throws IOException {
        if(Credit.maxtransfers(user) == -1) return;
        Scanner scan = new Scanner(System.in);
        double money = 0;
        while(true) {
            System.out.print("输入您要存入的金额:");
            try {
                money = scan.nextDouble();
                Less_than_fen.check(money);
                Less_than_zero.check(money);
                break;
            } catch (Less_than_fen e) {
                System.out.println(e.getMessage());
                continue;
            }catch (Less_than_zero m){
                System.out.println(m.getMessage());
                continue;
            }catch (InputMismatchException e) {
                System.out.println("请输入实数");
                scan.nextLine();
            }
        }
        if(Credit.maxwithdraw(user, money) == -1) return;
        user.money += money;
        user.credit += 4;
        user.transfers += 1;
        System.out.println("存款成功");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 存款%.2f元\n\n", Time.gettime(), user.card, user.name, money));
        Main.update();
        Wait.jixu();
    }
    public void withdraw(User user) throws IOException {
        if(Credit.maxtransfers(user) == -1) return;
        Scanner scan = new Scanner(System.in);
        double money;
        while(true) {
            System.out.print("输入您要取出的金额:");
            try {
                money = scan.nextDouble();
                Less_than_fen.check(money);
                Less_than_zero.check(money);
                break;
            } catch (Less_than_fen e) {
                System.out.println(e.getMessage());
                continue;
            }catch (Less_than_zero e) {
                System.out.println(e.getMessage());
                continue;
            }catch (InputMismatchException e) {
                System.out.println("请输入实数");
                scan.nextLine();
            }
        }
        if(Credit.maxwithdraw(user, money) == -1) return;
        if (money > user.money){
            System.out.println("您的余额不足");
            Wait.exit();
            return;
        }
        user.money -= money;
        user.credit += 4;
        user.transfers += 1;
        System.out.println("取款成功");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 取款%.2f元\n\n", Time.gettime(), user.card, user.name, money));
        Main.update();
        Wait.jixu();
    }
    public void transfer(HashSet<User> users, User user) throws IOException, Transfer_myself {
        if(Credit.maxtransfers(user) == -1) return;
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("输入对方卡号:");
            try {
                String card = scan.next();
                Transfer_myself.Check(card, user.card);
                break;
            } catch (Transfer_myself e) {
                e.getMessage();
                continue;
            }
        }
        int key = 0;
        for (User u : users){
            if (card.compareTo(u.card) == 0){
                key = 1;
                System.out.print("输入转账的金额:");
                while(true) {
                    System.out.print("输入转账的金额:");
                    try {
                        money = scan.nextDouble();
                        Less_than_fen.check(money);
                        Less_than_zero.check(money);
                        break;
                    } catch (Less_than_fen e) {
                        System.out.println(e.getMessage());
                        continue;
                    }catch (Less_than_zero e) {
                        System.out.println(e.getMessage());
                        continue;
                    }catch (InputMismatchException e) {
                        System.out.println("请输入实数");
                        scan.nextLine();
                    }
                }
                if (money > user.money){
                    System.out.println("您的余额不足");
                    Wait.exit();
                    return;
                }
                if (Credit.maxwithdraw(user, money) == -1) return;
                u.money += money;
                user.money -= money;
                System.out.println("转账成功");
                user.credit += 4;
                user.transfers += 1;
                Wait.jixu();
                Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 向卡号%s转账%.2lf元\n\n", Time.gettime(), user.card, user.name, u.card, money));
                Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 收到卡号:%s的转账%.2lf元\n\n", Time.gettime(), u.card, u.name, user.card, money));
                Main.update();
                return;
            }
        }
        if (key == 0){
            System.out.println("当前系统无该用户");
            Wait.exit();
            return;
        }
    }
    public void loan(User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        if (user.loan > 0){
            System.out.println("本系统不允许同一卡号多次贷款，除非还完上次贷款的钱");
            Wait.jixu();
            return;
        }
        while(true) {
            System.out.print("输入您要贷款的金额:");
            try {
                money = scan.nextDouble();
                Less_than_fen.check(money);
                Less_than_zero.check(money);
                break;
            } catch (Less_than_fen e) {
                System.out.println(e.getMessage());
                continue;
            } catch (Less_than_zero e) {
                System.out.println(e.getMessage());
                continue;
            }catch (InputMismatchException e) {
                System.out.println("请输入实数");
                scan.nextLine();
            }
        }
        if (Credit.maxloan(user, money) == -1) return;
        double daypayback = Credit.payback(user, money);
        int day = Credit.payday(user);
        System.out.printf("贷款功能开启后每天都要还款%.2f元，还%d天\n", daypayback, day);
        System.out.print("输入1确认，输入其他撤销:");
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
        if (key != 1) {
            Wait.exit();
            return;
        }
        user.loan += money;
        user.money += money;
        user.daypayback = daypayback;
        System.out.println("贷款成功");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 贷款%.2f元\n\n", Time.gettime(), user.card, user.name, money));
        Main.update();
        Wait.exit();
    }
    public void payback(User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        if (user.loan <= 0) {
            System.out.println("您当前没有欠款");
            Wait.exit();
            return;
        }
        if (user.ispayback == true) {
            System.out.println("您今日已还款");
            Wait.exit();
            return;
        }
        double money = user.daypayback;
        System.out.printf("是否确定还款%.2f元，输入1确定，输入其他建退出:", money);
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
        if (key != 1) {
            Wait.exit();
            return;
        }
        if (user.money < money) {
            System.out.println("您的余额不足");
            return;
        }
        user.credit += 4;
        user.money -= money;
        user.loan -= money;
        user.ispayback = true;
        System.out.println("还款成功");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 还款%.2f元\n\n", Time.gettime(), user.card, user.name, money));
        Main.update();
        Wait.exit();
    }
    public void freeze(HashSet<User> users, User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("确定冻结账户输入1，否则输入任意键:");
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
        if (key != 1){
            Wait.exit();
            return;
        }
        user.isfreeze = true;
        System.out.println("冻结成功");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 冻结卡号\n\n", Time.gettime(), user.card, user.name));
        Main.update();
    }

    public void exit(){
       Wait.exit();
    }

    public void changepassword(User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("输入您的登录密码:");
        String password = scan.next();
        if (password.compareTo(new String(user.password)) != 0) {
            System.out.println("密码错误");
            Wait.exit();
            return;
        }
        while(true) {
            while(true) {
                System.out.print("在这里输入您的新登录密码:");
                password = scan.next();
                if (Regist.islegal(password) == false) {
                    System.out.println("密码必须为6位阿拉伯数字，请重新输入");
                }
                else break;
            }
            System.out.print("再次输入您的登录密码:");
            String password1 = scan.next();
            if (password.compareTo(password1) != 0){
                System.out.println("两次密码输入不一致，提醒户主重新输入");
                continue;
            }
            else break;
        }
        user.password = new StringBuffer(password);
        System.out.println("修改密码成功");
        Main.update();
        Wait.exit();
    }
    @Override
    public int cancelaccout(HashSet<User> users, User user) throws IOException {
        if (user.money > 0){
            System.out.println("请取走所有钱，再注销");
            Wait.exit();
            return 1;
        }
        if (user.loan > 0){
            System.out.println("您还有欠款，不能注销");
            Wait.exit();
            return 1;
        }
        Scanner scan = new Scanner(System.in);
        System.out.print("您的姓名:");
        String name = scan.next();
        if (name.compareTo(user.name) != 0){
            Wait.error();
            return 1;
        }
        System.out.print("您的身份证号码:");
        String id = scan.next();
        if (id.compareTo(user.identity) != 0){
            Wait.error();
            return 1;
        }
        System.out.print("确定注销账户输入1，否则输入任意键:");
        int key = scan.nextInt();
        if (key != 1){
            Wait.exit();
            return 1;
        }
        Iterator it = users.iterator();
        while(it.hasNext()){
            if (it.next() == user){
                it.remove();
                break;
            }
        }
        System.out.println("注销成功！");
        Recordwrite.write(String.format("%s  卡号:%s 姓名:%s 注销成功\n\n", Time.gettime(), user.card, user.name));
        Wait.exit();
        Main.update();
        return 0;
    }
    public String getaName() {
        return this.name;
    }
    public  void setaName(String newName) {
        this.name = newName;
    }


    public int getaAge() {
        return this.age;
    }
    public  void setAge(int newAge) {
        this.age = newAge;
    }


    public String getaPhone() {
        return this.phone;
    }
    public  void setaPhone(String newPhone) {
        this.name = newPhone;
    }


    public String getaIdentity() {return this.identity;}
    public  void setaIdentity(String newIdentity) {this.name = newIdentity;}


    public int getacount() {return this.count;}
    public  void setacount(int newCount) {this.count = newCount;}



    public int getaCredit() {
        return this.credit;
    }
    public  void setaCredit(int newCredit) {
        this.credit = newCredit;
    }



    public int getaTransfer() {
        return this.transfers;
    }
    public  void setaTransfer(int newTransfer) {
        this.transfers = newTransfer;
    }



    public double getaLoan() {
        return this.loan;
    }
    public  void setaLoan(double newLoan) {
        this.loan = newLoan;
    }



    public  StringBuffer getaPassword() {
        return this.password;
    }
    public  void setaPassword(StringBuffer newPassword) {
        this.password = newPassword;
    }


    public  double getaMoney() {
        return this.money;
    }
    public  void setaMoney(Float newMoney) {
        this.money = newMoney;
    }

    public String getaCard() {return this.card;}
    public  void setaCard(String newCard) {this.card = newCard;}


    public boolean getaIspayback() {return this.ispayback;}
    public  void setaIspayback(boolean newIspayback) {this.ispayback = newIspayback;}


    public double getaDaypayback() {return this.daypayback;}
    public  void setaDaypayback(double newDaypayback) {this.daypayback = newDaypayback;}

    public boolean getaIsfreeze() {return this.isfreeze;}
    public  void setaIsfreeze(boolean newIsfreeze) {this.isfreeze = newIsfreeze;}

}
