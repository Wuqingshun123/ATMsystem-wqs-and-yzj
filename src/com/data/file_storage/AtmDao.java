package com.data.file_storage;
import com.ATMsystem.account.User;
import com.ATMsystem.interver.Wait;
import javax.xml.stream.XMLInputFactory;
import com.ATMsystem.account.Administor;
import com.ATMsystem.account.Account;


import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

//持久化,负责读写数据,缓存
import java.util.HashSet;

public class AtmDao {
       private FileLoadAndCommit flac = new FileLoadAndCommit("src\\com\\data\\record\\UserDate.txt");
       private HashSet<User> userBox;
       private boolean isDataLoaded = false;

       //从txt文件中获取用户数据
       public User getDate(HashSet<User> users, User inputUser) throws FileNotFoundException { // *************
              for (User user : users) {
                     if (user.equals(inputUser)) { // *************
                            return user;
                     }
              }
              return null;
       }

       //将内存中的用户数据储存到txt文件
       public static void updateDate(HashSet<User> users) throws IOException {

              FileOutputStream b1 = new FileOutputStream("src\\com\\data\\record\\UserDate.txt");
              FileOutputStream b2 = new FileOutputStream("src\\com\\data\\record\\UserDate.txt", true);
              BufferedOutputStream b = new BufferedOutputStream(b1);
              b1.write("".getBytes());

              FileLoadAndCommit a = new FileLoadAndCommit(null);
              AtmDao aa = new AtmDao();
              a.commit(users, b2);
              b.close();
              b1.close();
              //              if (!isDataLoaded) {
//                     userBox = flac.loadFile();
//                     isDataLoaded = true;
//              }
//              userBox.remove(user);
//              System.out.println(userBox.remove(user));
//              userBox.add(user);
//              flac.commit(userBox);
       }
       //删除文件数据
//       public void delete(User user) {
//              if (!isDataLoaded) {
//                     userBox = flac.loadFile();
//                     isDataLoaded = true;
//              }
//              userBox.remove(user);
//              flac.commit(userBox);
//       }
}