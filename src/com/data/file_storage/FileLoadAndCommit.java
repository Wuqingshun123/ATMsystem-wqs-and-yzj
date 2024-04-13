package com.data.file_storage;


import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;
import javax.xml.stream.XMLInputFactory;
import com.ATMsystem.account.Administor;
import com.ATMsystem.account.Account;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import static com.ATMsystem.main.Main.users;

//操作文件
public class FileLoadAndCommit {

    //文件路径
    private String pathName = null;

    public FileLoadAndCommit(String pathName) {
        this.pathName = pathName;
    }

            //将txt文件中的用户数据读入内存
            public HashSet<User> loadFile() throws FileNotFoundException {
                File f = new File("src\\com\\data\\record\\UserDate.txt");
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(" - ");
                        User user = new User();
                        user.card = parts[0]; // 设置卡号
                        user.password = new StringBuffer(parts[1]); // 设置密码
                        user.isfreeze = Boolean.parseBoolean(parts[2]); // 设置冻结状态
                        user.name = parts[3]; // 设置名字
                        user.age = Integer.parseInt(parts[4]); // 设置年龄
                        user.phone = parts[5]; // 设置电话号码
                        user.identity = parts[6]; // 设置身份证
                        user.money = Double.parseDouble(parts[7]); // 设置存款
                        user.count = Integer.parseInt(parts[8]); // 设置计数
                        user.credit = Integer.parseInt(parts[9]); // 设置信用
                        user.transfers = Integer.parseInt(parts[10]); // 设置转账
                        user.loan = Double.parseDouble(parts[11]); // 设置贷款
                        user.ispayback = Boolean.parseBoolean(parts[12]); // 设置是否偿还
                        user.daypayback = Double.parseDouble(parts[13]); // 设置每日偿还
                        users.add(user);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return users;
            }




    //将内存中的数据更新进入文件
    public void commit(HashSet<User> users, FileOutputStream f) {
        try (BufferedOutputStream bw = new BufferedOutputStream(f)) {
            for (User user : users) {
                String str = user.card + " - " + user.password + " - " + user.isfreeze + " - " + user.name + " - " + user.age + " - " + user.phone + " - " + user.identity + " - " + user.money + " - " + user.count + " - " + user.credit + " - " + user.transfers + " - " + user.loan + " - " + user.ispayback + " - " + user.daypayback+"\n";
                bw.write(str.getBytes());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






//
//
//
//
//
//
//
//

//
//package com.data.file_storage;
//
//
//import com.ATMsystem.account.User;
//import com.ATMsystem.interver.Wait;
//import javax.xml.stream.XMLInputFactory;
//import com.ATMsystem.account.Administor;
//import com.ATMsystem.account.Account;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//
//import static com.ATMsystem.main.Main.users;
//
////操作文件
//public class FileLoadAndCommit {
//
//    //文件路径
//    private String pathName = null;
//
//    public FileLoadAndCommit(String pathName) {
//        this.pathName = pathName;
//    }
//
//    //将txt文件中的用户数据读入内存
//    public HashSet<User> loadFile() {
//        HashSet<User> userBox = new HashSet<User>();
//        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(" - ");
//                User user = new User();
//                user.card = parts[0]; // 设置卡号
//                user.password = new StringBuffer(parts[1]); // 设置密码
//                user.isfreeze = Boolean.parseBoolean(parts[2]); // 设置冻结状态
//                user.name = parts[3]; // 设置名字
//                user.age = Integer.parseInt(parts[4]); // 设置年龄
//                user.phone = parts[5]; // 设置电话号码
//                user.identity = parts[6]; // 设置身份证
//                user.money = Double.parseDouble(parts[7]); // 设置存款
//                user.count = Integer.parseInt(parts[8]); // 设置计数
//                user.credit = Integer.parseInt(parts[9]); // 设置信用
//                user.transfers = Integer.parseInt(parts[10]); // 设置转账
//                user.loan = Double.parseDouble(parts[11]); // 设置贷款
//                user.ispayback = Boolean.parseBoolean(parts[12]); // 设置是否偿还
//                user.daypayback = Double.parseDouble(parts[13]); // 设置每日偿还
//                users.add(user);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
//
//
//
//
//    //将内存中的数据更新进入文件
////    public void commit(HashSet<User> userBox) {
////        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
////            for (User user : userBox) {
////                bw.write(user.card + " - " + user.password + " - " + user.isfreeze + " - " + user.name + " - " + user.age + " - " + user.phone + " - " + user.identity + " - " + user.money + " - " + user.count + " - " + user.credit + " - " + user.transfers + " - " + user.loan + " - " + user.ispayback + " - " + user.daypayback);
////                bw.newLine();
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//}